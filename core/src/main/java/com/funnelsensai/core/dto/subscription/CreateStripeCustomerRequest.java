package com.funnelsensai.core.dto.subscription;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStripeCustomerRequest {
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String paymentMethodType;
} 