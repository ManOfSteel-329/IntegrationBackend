package com.funnelsensai.core.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.stereotype.Service;
import com.funnelsensai.core.domain.User;
import java.util.List;

@Service
public class PaymentService {
    private final UserService userService;

    public PaymentService(UserService userService) {
        this.userService = userService;
    }

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

    public PaymentIntent createSubscription(Long amount, String currency, 
            User user, String description, String selectedPlan) throws StripeException {
        // 1. Create or retrieve Stripe Customer
        CustomerCreateParams customerParams = CustomerCreateParams.builder()
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
            
        Customer customer = Customer.create(customerParams);
        
        // Store Stripe Customer ID with user
        user.setStripeCustId(customer.getId());
        userService.save(user);

        // 2. Create PaymentIntent
        PaymentIntentCreateParams paymentIntentParams = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .setCustomer(customer.getId())
            .setDescription(description)
            .putMetadata("plan", selectedPlan)
            .addPaymentMethodType("card")
            .build();

        return PaymentIntent.create(paymentIntentParams);
    }

    private String getPriceIdForPlan(String planName) {
        return switch (planName) {
            case "Basic" -> "price_basic_id_from_stripe";
            case "Pro" -> "price_pro_id_from_stripe";
            default -> throw new IllegalArgumentException("Invalid plan name");
        };
    }
} 