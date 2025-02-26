package com.funnelsensai.core.service.Stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.param.*;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;

import com.stripe.model.SetupIntent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class StripeService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.price.id.pro}")
    private String priceIdPro;

    @Value("${stripe.price.id.basic}")
    private String priceIdBasic;

    public static final String PLAN_PRO = "Pro";
    public static final String PLAN_BASIC = "Basic";

    @PostConstruct
    private void initializeStripe() {
        if (stripeSecretKey == null || stripeSecretKey.isEmpty()) {
            throw new IllegalStateException("Stripe secret key is not configured properly.");
        }
        Stripe.apiKey = stripeSecretKey;
    }

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
            case PLAN_PRO -> priceIdPro;
            case PLAN_BASIC -> priceIdBasic;
            default -> throw new IllegalArgumentException("Invalid plan name");
        };
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

    public Customer createCustomer(String email, String name) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
            .setEmail(email)
            .setName(name)
            .build();

        return Customer.create(params);
    }

    public SetupIntent createSetupIntent(String paymentMethodType) throws StripeException {
        SetupIntentCreateParams params = SetupIntentCreateParams.builder()
            .addPaymentMethodType(paymentMethodType)
            .build();
        return SetupIntent.create(params);
    }
}
