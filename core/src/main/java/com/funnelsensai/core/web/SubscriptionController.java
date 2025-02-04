package com.funnelsensai.core.web;


import com.funnelsensai.core.service.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.funnelsensai.core.dto.subscription.*;
import com.stripe.model.Customer;
import java.util.Map;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.model.SetupIntent;
import com.stripe.param.SetupIntentCreateParams;

@RestController
@RequestMapping("/auth")
public class SubscriptionController {

    private final StripeService stripeService;

    public SubscriptionController(StripeService stripeService) {
        this.stripeService = stripeService;
    }


    @PostMapping("/create-customer-and-setup-intent")
    public ResponseEntity<?> createCustomerAndSetupIntent(@RequestBody CreateStripeCustomerRequest request) {
        try {
            if (request.getEmail() == null || request.getName() == null) {

                return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields"));
            }

            Customer customer = stripeService.createCustomer(request.getEmail(), request.getName());
            System.out.println("Customer created: " + customer.getId());


            SetupIntentCreateParams params = SetupIntentCreateParams.builder()
                .addPaymentMethodType("card")
                // .setCustomer(customer.getId()) //if I do this, do I have to attach later?
                .build();

            SetupIntent setupIntent = SetupIntent.create(params);
            System.out.println("Setup intent created: " + setupIntent.getClientSecret());
            return ResponseEntity.ok(Map.of(
            "clientSecret", setupIntent.getClientSecret(),
            "customerId", customer.getId()
        ));

        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating customer and setup intent: " + e.getMessage()));
        }
    }

    /* @PostMapping("/create-setup-intent")
    public ResponseEntity<?> createSetupIntent() {
        try {
            SetupIntentCreateParams params = SetupIntentCreateParams.builder()
                .addPaymentMethodType("card")
                .build();

            SetupIntent setupIntent = SetupIntent.create(params);

            return ResponseEntity.ok(Map.of("clientSecret", setupIntent.getClientSecret()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating setup intent: " + e.getMessage()));
        }
    } */

    @PostMapping("/attach-payment-method-and-create-subscription")
    public ResponseEntity<?> attachPaymentMethodAndCreateSubscription(@RequestBody CreateSubscriptionRequest request) {
        
        try {

            System.out.println("Price ID for plan: " + stripeService.getPriceIdForPlan(request.getPlanName()));
            
            stripeService.attachPaymentMethodToCustomer(request.getCustomerId(), request.getPaymentMethodId());
            stripeService.setDefaultPaymentMethodForCustomer(request.getCustomerId(), request.getPaymentMethodId());

            System.out.println("Price ID for plan: " + stripeService.getPriceIdForPlan(request.getPlanName()));

            SubscriptionCreateParams params = SubscriptionCreateParams.builder()
                    .setCustomer(request.getCustomerId())
                    .addItem(
                            SubscriptionCreateParams.Item.builder()
                                    .setPrice(stripeService.getPriceIdForPlan(request.getPlanName()))
                                    .build())




                    .build();
            Subscription subscription = Subscription.create(params);
            System.out.println("Subscription created: " + subscription.getId());
        

            return ResponseEntity.ok(Map.of("subscriptionId", subscription.getId()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error attaching payment method and creating subscription: " + e.getMessage()));
        }
    }

   /*  @PostMapping("/create-subscription")
    public ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        System.out.println("Received create-subscription request: " + request);
        try {

            SubscriptionCreateParams params = SubscriptionCreateParams.builder()
                    .setCustomer(request.getCustomerId())
                    .addItem(
                            SubscriptionCreateParams.Item.builder()
                                    .setPrice(request.getPriceId())
                                    .build())

                    .build();
            Subscription subscription = Subscription.create(params);
            
            return ResponseEntity.ok(Map.of("subscriptionId", subscription.getId()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating subscription: " + e.getMessage()));
        }
    } */
}
