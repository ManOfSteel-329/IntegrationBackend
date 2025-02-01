package com.funnelsensai.core.dto.subscription;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubscriptionRequest {
    @NotNull
    private String priceId;  // Stripe Price ID
    private String customerId;  // Optional - if customer exists
    private PaymentMethodDetails paymentMethod;
}

@Data
class PaymentMethodDetails {
    private String type = "card";
    private CardDetails card;
}

@Data
class CardDetails {
    private String number;
    private Integer expMonth;
    private Integer expYear;
    private String cvc;
}