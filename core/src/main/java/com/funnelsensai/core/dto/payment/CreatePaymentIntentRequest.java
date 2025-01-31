package com.funnelsensai.core.dto.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CreatePaymentIntentRequest {
    @NotNull
    @Min(1)
    private Long amount;
    
    @NotNull
    private String currency;
    
    private String description;
    
    @NotNull
    private String selectedPlan;
} 