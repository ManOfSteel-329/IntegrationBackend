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
import com.stripe.model.Customer;
import com.funnelsensai.core.domain.User;
import java.util.Map;
import com.funnelsensai.core.dto.payment.CreatePaymentMethodRequest;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.model.SetupIntent;
import com.stripe.param.SetupIntentCreateParams;

@RestController
@RequestMapping("/auth/subscription")
public class SubscriptionController {

    private final UserService userService;
    private final PaymentService paymentService;

    public SubscriptionController(UserService userService, PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    /*
     * @PostMapping("/create")
     * public ResponseEntity<SubscriptionResponse>
     * createSubscription(@Valid @RequestBody CreateSubscriptionRequest request) {
     * try {
     * // Create a new customer
     * User user = userService.findById(request.getUserId());
     * Customer customer = paymentService.createStripeCustomer(user,
     * request.getPaymentMethod().getPaymentToken());
     * 
     * // Create the subscription with the new customer ID
     * Subscription subscription = paymentService.createSubscription(
     * customer.getId(),
     * request.getPriceId(),
     * request.getPaymentMethod().getPaymentToken()
     * );
     * 
     * return ResponseEntity.ok(SubscriptionResponse.builder()
     * .success(true)
     * .subscriptionId(subscription.getId())
     * .status(subscription.getStatus())
     * .build());
     * 
     * } catch (StripeException e) {
     * return ResponseEntity.badRequest().body(SubscriptionResponse.builder()
     * .success(false)
     * .error("Payment processing error: " + e.getMessage())
     * .build());
     * }
     * }
     */

    @PostMapping("/create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody CreateStripeCustomerRequest request) {
        try {
            // Validate request data
            if (request.getEmail() == null || request.getName() == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields"));
            }

            // Create a new customer in Stripe
            Customer customer = paymentService.createCustomer(request.getEmail(), request.getName());

            // Return the customer ID to the frontend
            return ResponseEntity.ok(Map.of("customerId", customer.getId()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating customer: " + e.getMessage()));
        }
    }

    /*
     * @PostMapping("/create-payment-method")
     * public ResponseEntity<?> createPaymentMethod(@RequestBody
     * CreatePaymentMethodRequest request) {
     * try {
     * // Validate request data
     * if (request.getCustomerId() == null || request.getPaymentToken() == null) {
     * return ResponseEntity.badRequest().body(Map.of("error",
     * "Missing required fields"));
     * }
     * 
     * // Create a PaymentMethod with the provided token
     * PaymentMethod paymentMethod =
     * paymentService.createPaymentMethod(request.getCustomerId(),
     * request.getPaymentToken());
     * 
     * // Return the PaymentMethod ID to the frontend
     * return ResponseEntity.ok(Map.of("paymentMethodId", paymentMethod.getId()));
     * } catch (StripeException e) {
     * return ResponseEntity.badRequest().body(Map.of("error",
     * "Error creating payment method: " + e.getMessage()));
     * }
     * }
     */

    @PostMapping("/create-subscription")
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
            // Retrieve the customer to get the default PaymentMethod ID
            // Customer customer = paymentService.retrieveCustomer(request.getCustomerId());
            // String paymentMethodId =
            // customer.getInvoiceSettings().getDefaultPaymentMethod();
            // String paymentIntentId = request.getPaymentIntentId();

            // System.out.println("paymentIntentId: " + paymentIntentId);

            // Attach the PaymentMethod to the Customer
            // paymentService.attachPaymentMethodToCustomer(request.getCustomerId(),
            // paymentMethodId);

            // Set the PaymentMethod as the default payment method for the customer
            // paymentService.setDefaultPaymentMethodForCustomer(request.getCustomerId(),
            // paymentMethodId);

            // Create a subscription in Stripe
            // Subscription subscription = paymentService.createSubscription(
            //         request.getCustomerId(),
            //         request.getPriceId(),
            //         request.getPaymentMethod(),
            //         request.getPlanName()
            // // request.getPaymentIntentId()
            // );

            // Return the subscription ID to the frontend
            return ResponseEntity.ok(Map.of("subscriptionId", subscription.getId()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating subscription: " + e.getMessage()));
        }
    }

    /*
     * @PostMapping("/create-customer-and-subscription")
     * public ResponseEntity<?> createCustomerAndSubscription(@RequestBody
     * CreateSubscriptionRequest request) {
     * try {
     * User user = userService.findById(request.getUserId());
     * 
     * // Retrieve the PaymentIntent
     * PaymentIntent paymentIntent =
     * paymentService.retrievePaymentIntent(request.getPaymentIntentId());
     * 
     * // Get the PaymentMethod ID from the PaymentIntent
     * String paymentMethodId = paymentIntent.getPaymentMethod();
     * 
     * // Create a new customer with the PaymentMethod ID
     * Customer customer = paymentService.createStripeCustomer(user,
     * paymentMethodId);
     * 
     * // Create the subscription with the new customer ID and PaymentMethod ID
     * Subscription subscription =
     * paymentService.createSubscription(customer.getId(), request.getPriceId(),
     * paymentMethodId);
     * 
     * return ResponseEntity.ok(Map.of("subscriptionId", subscription.getId()));
     * } catch (StripeException e) {
     * return ResponseEntity.badRequest().body(Map.of("error",
     * "Error creating customer and subscription: " + e.getMessage()));
     * }
     * }
     */

    /*
     * @PostMapping("/create-checkout-session")
     * public ResponseEntity<?> createCheckoutSession(@RequestBody
     * CreateSubscriptionRequest request) {
     * 
     * System.out.println("Received create-checkout-session request: " + request);
     * try {
     * // Create a Checkout Session
     * SessionCreateParams params = SessionCreateParams.builder()
     * 
     * .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
     * .setSuccessUrl("http://localhost:8080/success")
     * // .setCancelUrl("http://localhost:8080/cancel")
     * 
     * .addLineItem(
     * SessionCreateParams.LineItem.builder()
     * .setPrice(request.getPriceId())
     * .setQuantity(1L)
     * .build()
     * )
     * .setCustomerEmail(request.getEmail()) // Assuming email is part of the
     * request
     * .build();
     * 
     * Session session = Session.create(params);
     * 
     * // Return the session URL to the frontend
     * return ResponseEntity.ok(Map.of("checkoutUrl", session.getUrl()));
     * } catch (StripeException e) {
     * return ResponseEntity.badRequest().body(Map.of("error",
     * "Error creating checkout session: " + e.getMessage()));
     * }
     * }
     */

    @PostMapping("/create-setup-intent")
    public ResponseEntity<?> createSetupIntent() {
        try {
            SetupIntentCreateParams params = SetupIntentCreateParams.builder()
                .addPaymentMethodType("card")
                .build();

            SetupIntent setupIntent = SetupIntent.create(params);

            // Return the client secret to the frontend
            return ResponseEntity.ok(Map.of("clientSecret", setupIntent.getClientSecret()));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating setup intent: " + e.getMessage()));
        }
    }

    @PostMapping("/attach-payment-method")
    public ResponseEntity<?> attachPaymentMethod(@RequestBody Map<String, String> request) {
        String customerId = request.get("customerId");
        String paymentMethodId = request.get("paymentMethodId");

        try {
            // PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);

            /* PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder()
                .setCustomer(customerId)
                .build();

            paymentMethod.attach(attachParams); */

            // Optionally, set the payment method as the default for the customer
            //Customer customer = Customer.retrieve(customerId);
            //customer.update(Map.of("invoice_settings", Map.of("default_payment_method", paymentMethodId)));

            paymentService.attachPaymentMethodToCustomer(customerId, paymentMethodId);
            paymentService.setDefaultPaymentMethodForCustomer(customerId, paymentMethodId);
            


            return ResponseEntity.ok(Map.of("success", true));
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error attaching payment method: " + e.getMessage()));
        }
    }
}
