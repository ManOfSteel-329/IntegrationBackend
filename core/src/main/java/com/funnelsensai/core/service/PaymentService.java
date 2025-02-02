package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.subscription.CreateSubscriptionRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.param.*;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final UserService userService;

    public PaymentService(UserService userService) {
        this.userService = userService;
    }

    // Create a one-time payment
    public PaymentIntent createPaymentIntent(Long amount, String currency, String description) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .setDescription(description)
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                    .setEnabled(true)
                    .build()
            )
            .build();

        return PaymentIntent.create(params);
    }

    // Create a new subscription and ensure payment method is attached
    public Subscription createSubscription(CreateSubscriptionRequest request) throws StripeException {
        if (request.getCustomerId() == null || request.getPaymentMethod() == null) {
            throw new IllegalArgumentException("Customer ID and Payment Method are required");
        }

        // Attach payment method to customer before subscription creation
        retrieveAndAttachPaymentMethod(request.getPaymentMethod().getPaymentToken(), request.getCustomerId());

        // Build subscription params
        SubscriptionCreateParams params = SubscriptionCreateParams.builder()
            .setCustomer(request.getCustomerId())
            .addItem(SubscriptionCreateParams.Item.builder()
                .setPrice(getPriceIdForPlan(request.getPlanName())) // Get price ID for plan
                .build())
            .setDefaultPaymentMethod(request.getPaymentMethod().getPaymentToken())
            .build();

        return Subscription.create(params);
    }

    // Retrieve and attach a payment method to a customer
    public PaymentMethod retrieveAndAttachPaymentMethod(String paymentToken, String customerId) throws StripeException {
        if (paymentToken == null || paymentToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid payment token");
        }

        // Retrieve PaymentMethod from Stripe
        PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentToken);

        // Attach the payment method to the customer
        PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder()
            .setCustomer(customerId)
            .build();
        paymentMethod.attach(attachParams);

        return paymentMethod;
    }

    // Create a Stripe Customer if not already exists
    public Customer createStripeCustomer(User user) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
            .setEmail(user.getEmail())
            .setName(user.getFirstName() + " " + user.getLastName())
            .setAddress(CustomerCreateParams.Address.builder()
                .setLine1(user.getAddress().getLine1())
                .setCity(user.getAddress().getCity())
                .setState(user.getAddress().getState())
                .setPostalCode(user.getAddress().getPostalCode())
                .setCountry(user.getAddress().getCountry())
                .build())
            .build();

        Customer customer = Customer.create(params);
        user.setStripeCustId(customer.getId());
        userService.save(user); // Save customer ID for future transactions
        return customer;
    }

    // Get Stripe price ID for a given plan name
    private String getPriceIdForPlan(String planName) {
        return switch (planName) {
            case "FunnelSensai Pro" -> "price_1QngiQDVAigQtw1EOTyUC8TX";
            case "FunnelSensai Basic" -> "price_1QnghUDVAigQtw1EXLFzR2Ob";
            default -> throw new IllegalArgumentException("Invalid plan name");
        };
    }
}
