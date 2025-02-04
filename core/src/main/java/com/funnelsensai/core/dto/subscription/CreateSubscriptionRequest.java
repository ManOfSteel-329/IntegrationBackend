package com.funnelsensai.core.dto.subscription;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubscriptionRequest {
    @NotNull
    private String priceId;
    private String customerId;
    private String planName;
    private PaymentMethodDetails paymentMethod;
    private Long userId;
    private Long amount;
    private String paymentIntentId;
    private String email;
}