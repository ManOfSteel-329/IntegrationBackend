package com.funnelsensai.core.dto.subscription;

import lombok.Data;

@Data
public class PaymentMethodDetails {
    private String type = "card";
    private String paymentToken;
    private CardDetails card;
}