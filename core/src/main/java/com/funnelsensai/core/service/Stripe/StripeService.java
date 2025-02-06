package com.funnelsensai.core.service.Stripe;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.subscription.CreateSubscriptionRequest;
import com.funnelsensai.core.service.UserService;
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
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import com.stripe.model.SetupIntent;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class StripeService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey; //ADD VALIDATION CHECK

    private final UserService userService;

    public StripeService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void initializeStripe() {
        if (stripeSecretKey == null || stripeSecretKey.isEmpty()) {
            throw new IllegalStateException("Stripe secret key is not configured properly.");
        }
        Stripe.apiKey = stripeSecretKey;
    }

    // Create a new subscription and ensure payment method is attached
    public Subscription createSubscription(String customerId, String planName) throws StripeException {
        SubscriptionCreateParams params = SubscriptionCreateParams.builder()
                .setCustomer(customerId)
                .addItem(
                        SubscriptionCreateParams.Item.builder()
                                .setPrice(getPriceIdForPlan(planName))
                                .build())
                .build();

        return Subscription.create(params);
    }

    private String getPriceIdForPlan(String planName) {
        return switch (planName) {
            case "Pro" -> "price_1QngiQDVAigQtw1EOTyUC8TX";
            case "Basic" -> "price_1QnghUDVAigQtw1EXLFzR2Ob";
            default -> throw new IllegalArgumentException("Invalid plan name");
        };
    }

    public void attachAndSetDefaultPaymentForCustomer(String customerId, String paymentMethodId) throws StripeException {
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

    //todo: change to first + last name
    public Customer createCustomer(String email, String name) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
            .setEmail(email)
            .setName(name)
            .build();

        return Customer.create(params);
    }

    public SetupIntent createSetupIntent() throws StripeException {
        SetupIntentCreateParams params = SetupIntentCreateParams.builder()
            .addPaymentMethodType("card")
            .build();
        return SetupIntent.create(params);
    }
}
