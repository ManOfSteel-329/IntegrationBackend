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
        try {

            if (request.getEmail() == null || request.getName() == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields"));
            }

            // Check if the email already exists
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
        try {
            stripeService.attachPaymentMethodToCustomer(request.getCustomerId(), request.getPaymentMethodId());
            stripeService.setDefaultPaymentMethodForCustomer(request.getCustomerId(), request.getPaymentMethodId());

            Subscription subscription = stripeService.createSubscription(request.getCustomerId(), request.getPlanName());

            Company company = companyService.createCompany(request.getCompanyName(), request.getCustomerId(), subscription.getId());

            userService.createUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), company, Role.ADMIN);

            return ResponseEntity.ok(Map.of("subscriptionId", subscription.getId(), "company", company));

        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error attaching payment method and creating subscription: " + e.getMessage()));
        }
    }

}
