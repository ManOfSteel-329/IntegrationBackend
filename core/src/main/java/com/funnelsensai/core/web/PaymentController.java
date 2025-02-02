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
import com.stripe.model.Subscription;
import com.funnelsensai.core.dto.subscription.CreateSubscriptionRequest;
import com.funnelsensai.core.dto.subscription.SubscriptionResponse;
import com.funnelsensai.core.dto.subscription.ErrorResponse;
import org.springframework.http.MediaType;
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

    @PostMapping("/create-payment-intent")
    public CreatePaymentIntentResponse createPaymentIntent(@RequestBody CreatePaymentIntentRequest request) throws StripeException {
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(
            request.getAmount(),
            request.getCurrency(),
            request.getDescription()
        );
        
        return new CreatePaymentIntentResponse(paymentIntent.getClientSecret());
    }
} 