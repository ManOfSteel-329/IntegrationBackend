package com.funnelsensai.core.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.funnelsensai.core.dto.subscription.*;
import com.funnelsensai.core.service.CompanyService;
import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.service.Stripe.StripeService;
import com.stripe.model.Customer;
import java.util.Map;
import com.stripe.model.SetupIntent;
import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.Role;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.funnelsensai.core.domain.User;

@CrossOrigin(origins = "https://2fca-47-37-127-19.ngrok-free.app") //for testing purposes, change based on provided ngrok url
@RestController
@RequestMapping("/auth/signup")
public class SubscriptionController {

    private final StripeService stripeService;
    private final UserService userService;
    private final CompanyService companyService;

    public SubscriptionController(StripeService stripeService, UserService userService, CompanyService companyService) {
        this.stripeService = stripeService;
        this.userService = userService;
        this.companyService = companyService;
    }

    @PostMapping("/create-customer-and-setup-intent")
    public ResponseEntity<?> createCustomerAndSetupIntent(@RequestBody CreateStripeCustomerRequest request) {

        System.out.println(request);
        try {
            if (request.getEmail() == null || request.getName() == null || request.getPaymentMethodType() == null || request.getPaymentMethodType().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields or payment method type"));
            }

            if (userService.emailExists(request.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
            }

            Customer customer = stripeService.createCustomer(request.getEmail(), request.getName());
            SetupIntent setupIntent = stripeService.createSetupIntent(request.getPaymentMethodType());

            return ResponseEntity.ok(Map.of(
                "clientSecret", setupIntent.getClientSecret(),
                "customerId", customer.getId()
            ));

        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating customer and setup intent: " + e.getMessage()));
        }
    }

    @PostMapping("/attach-payment-method-and-create-subscription")
    public ResponseEntity<?> attachPaymentMethodAndCreateSubscription(@RequestBody CreateSubscriptionRequest request) {
        
        System.out.println(request);
        try {
            String customerId = request.getCustomerId();
            String paymentMethodId = request.getPaymentMethodId();
            String planName = request.getPlanName();
            String companyName = request.getCompanyName();
            String email = request.getEmail();
            String password = request.getPassword();
            
            String accountFirstName = request.getAccountFirstName();
            String accountLastName = request.getAccountLastName();
            
            String cardholderFirstName = request.getCardholderFirstName();
            String cardholderLastName = request.getCardholderLastName();

            stripeService.attachPaymentMethodToCustomer(customerId, paymentMethodId);

            stripeService.setDefaultPaymentMethodForCustomer(customerId, paymentMethodId);

            Subscription subscription = stripeService.createSubscription(customerId, planName);

            Company company = companyService.createCompany(
                companyName, 
                customerId,
                subscription.getId()
            );

            User user = userService.createUser(
                email, 
                password, 
                accountFirstName, 
                accountLastName, 
                company,
                Role.ADMIN
            );

            return ResponseEntity.ok().body(Map.of(
                "subscriptionId", subscription.getId(),
                "userId", user.getId(),
                "companyId", company.getId()
            ));

        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error processing subscription: " + e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating user or company: " + e.getMessage()));
        }
    }

}
