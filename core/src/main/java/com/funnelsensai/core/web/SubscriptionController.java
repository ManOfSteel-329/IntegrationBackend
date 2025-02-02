package com.funnelsensai.core.web;

import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.funnelsensai.core.dto.subscription.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/subscription")
public class SubscriptionController {

    private final UserService userService;
    private final PaymentService paymentService;

    public SubscriptionController(UserService userService, PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody CreateSubscriptionRequest request) {
        // Validate request
        if (request == null || request.getPaymentMethod() == null) {
            return ResponseEntity.badRequest().body(SubscriptionResponse.builder()
                .success(false)
                .error("Invalid request: Missing payment details")
                .build());
        }

        String paymentToken = request.getPaymentMethod().getPaymentToken();
        if (paymentToken == null || paymentToken.isEmpty()) {
            return ResponseEntity.badRequest().body(SubscriptionResponse.builder()
                .success(false)
                .error("Invalid request: Missing payment token")
                .build());
        }

        try {
            // Call the service layer to handle payment method retrieval & attachment
            PaymentMethod paymentMethod = paymentService.retrieveAndAttachPaymentMethod(paymentToken, request.getCustomerId());

            // Set payment type in request
            request.getPaymentMethod().setType(paymentMethod.getType());

            // Create the subscription
            Subscription subscription = paymentService.createSubscription(request);

            boolean requiresAction = false;
            String clientSecret = null;

            if (subscription.getLatestInvoice() != null) {
                Invoice invoice = Invoice.retrieve(subscription.getLatestInvoice()); // Retrieve the invoice
                if (invoice.getPaymentIntent() != null) {
                    PaymentIntent intent = PaymentIntent.retrieve(invoice.getPaymentIntent());
                    if ("requires_action".equals(intent.getStatus()) || "requires_payment_method".equals(intent.getStatus())) {
                        requiresAction = true;
                        clientSecret = intent.getClientSecret();
                    }
                }
            }

            return ResponseEntity.ok(SubscriptionResponse.builder()
                .success(true)
                .requiresAction(requiresAction)
                .clientSecret(clientSecret)
                .subscriptionId(subscription.getId())
                .status(subscription.getStatus())
                .build());

        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(SubscriptionResponse.builder()
                .success(false)
                .error("Payment processing error: " + e.getMessage())
                .build());
        }
    }
}
