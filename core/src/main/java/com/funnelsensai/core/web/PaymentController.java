package com.funnelsensai.core.web;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.payment.CreatePaymentIntentRequest;
import com.funnelsensai.core.dto.payment.UserRegistrationRequest;
import com.funnelsensai.core.dto.payment.SubscriptionRequest;
import com.funnelsensai.core.dto.payment.CreatePaymentIntentResponse;
import com.funnelsensai.core.service.PaymentService;
import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.domain.Address;
import com.stripe.model.PaymentIntent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/auth/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public PaymentController(PaymentService paymentService, 
                           UserService userService,
                           PasswordEncoder passwordEncoder) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create-subscription")
    public ResponseEntity<CreatePaymentIntentResponse> createSubscription(
            @RequestBody SubscriptionRequest request) {
        try {
            // Create Address object
            Address address = new Address();
            address.setLine1(request.getRegistration().getLine1());
            address.setCity(request.getRegistration().getCity());
            address.setState(request.getRegistration().getState());
            address.setPostalCode(request.getRegistration().getPostalCode());
            address.setCountry(request.getRegistration().getCountry());

            // Create and save User
            User user = new User();
            user.setEmail(request.getRegistration().getEmail());
            user.setFirstName(request.getRegistration().getFirstName());
            user.setLastName(request.getRegistration().getLastName());
            user.setCompanyName(request.getRegistration().getCompanyName());
            user.setPassword(passwordEncoder.encode(request.getRegistration().getPassword()));
            user.setAddress(address);
            
            // Save user to get ID and other generated fields
            user = userService.save(user);

            // Create subscription with Stripe
            PaymentIntent paymentIntent = paymentService.createSubscription(
                request.getPayment().getAmount(),
                request.getPayment().getCurrency(),
                user,
                request.getPayment().getDescription(),
                request.getPayment().getSelectedPlan()
            );

            // Return client secret for frontend processing
            CreatePaymentIntentResponse response = new CreatePaymentIntentResponse();
            response.setClientSecret(paymentIntent.getClientSecret());
            
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 