package com.funnelsensai.core.web;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<?> handleStripeEvent(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        String endpointSecret = "your_webhook_secret"; // Replace with your actual webhook secret

        try {
            Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);

            if ("checkout.session.completed".equals(event.getType())) {
                Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
                if (session != null) {
                    // Handle successful checkout session
                    // Update your application state, e.g., mark subscription as active
                }
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Webhook error: " + e.getMessage());
        }
    }
} 