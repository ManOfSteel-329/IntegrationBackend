package com.funnelsensai.core.dto.payment;

import lombok.Data;

@Data
public class CreatePaymentIntentResponse {
    private String clientSecret;
} 