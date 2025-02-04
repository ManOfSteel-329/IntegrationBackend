package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.subscription.CreateSubscriptionRequest;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.param.*;
import com.stripe.model.Price;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodAttachParams;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final UserService userService;

    public PaymentService(UserService userService) {
        this.userService = userService;
    }

    /* // Create a payment method
    public PaymentMethod createPaymentMethod(String paymentToken) throws StripeException {
        PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
            .setType(PaymentMethodCreateParams.Type.CARD)
            .setCard(PaymentMethodCreateParams.Card.builder()
                .setToken(paymentToken)
                .build())
            .build();

        return PaymentMethod.create(params);
    } */

    // Create a new subscription and ensure payment method is attached
    public Subscription createSubscription(String customerId, String priceId, String paymentMethodId, String planName) throws StripeException {
        SubscriptionCreateParams params = SubscriptionCreateParams.builder()
            .setCustomer(customerId)
            .addItem(SubscriptionCreateParams.Item.builder()
                .setPrice(priceId)
                .setPlan(planName)
                .build())
            .setDefaultPaymentMethod(paymentMethodId)
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
    public Customer createStripeCustomer(User user, String paymentMethodId) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
            .setEmail(user.getEmail())
            .setName(user.getFirstName() + " " + user.getLastName())
            .setPaymentMethod(paymentMethodId)
            .setInvoiceSettings(
                CustomerCreateParams.InvoiceSettings.builder()
                    .setDefaultPaymentMethod(paymentMethodId)
                    .build()
            )
            .build();

        /* Customer customer = Customer.create(params);
        user.setStripeCustId(customer.getId());
        userService.save(user); // Save customer ID for future transactions
        return customer; */

        return Customer.create(params);
    }

    // Get Stripe price ID for a given plan name
    private String getPriceIdForPlan(String planName) {
        return switch (planName) {
            case "FunnelSensai Pro" -> "price_1QngiQDVAigQtw1EOTyUC8TX";
            case "FunnelSensai Basic" -> "price_1QnghUDVAigQtw1EXLFzR2Ob";
            default -> throw new IllegalArgumentException("Invalid plan name");
        };
    }

    /* public PaymentMethod createPaymentMethodFromToken(String token) throws StripeException {
        PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
            .setType(PaymentMethodCreateParams.Type.CARD)
            .setCard(PaymentMethodCreateParams.Card.builder()
                .setToken(token)
                .build())
            .build();

        return PaymentMethod.create(params);
    } */

    public PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
        return PaymentIntent.retrieve(paymentIntentId);
    }

    public void attachPaymentMethodToCustomer(String customerId, String paymentMethodId) throws StripeException {
        PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);
        PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder()
            .setCustomer(customerId)
            .build();
        paymentMethod.attach(attachParams);
    }

    public void setDefaultPaymentMethodForCustomer(String customerId, String paymentMethodId) throws StripeException {
        Customer customer = Customer.retrieve(customerId);
        CustomerUpdateParams updateParams = CustomerUpdateParams.builder()
            .setInvoiceSettings(CustomerUpdateParams.InvoiceSettings.builder()
                .setDefaultPaymentMethod(paymentMethodId)
                .build())
            .build();
        customer.update(updateParams);
    }

    public Long getAmountByPriceId(String priceId) throws StripeException {
        // Retrieve the price object from Stripe
        Price price = Price.retrieve(priceId);
        // Return the unit amount in cents
        return price.getUnitAmount();
    }

    public Customer createCustomer(String email, String name) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
            .setEmail(email)
            .setName(name)
            .build();

        return Customer.create(params);
    }

    public Customer retrieveCustomer(String customerId) throws StripeException {
        return Customer.retrieve(customerId);
    }
}
