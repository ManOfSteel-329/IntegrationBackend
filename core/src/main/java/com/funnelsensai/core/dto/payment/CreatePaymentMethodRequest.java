package com.funnelsensai.core.dto.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CreatePaymentMethodRequest {
    @NotNull
    @Min(1)
    private Long amount;
    
    @NotNull
    private String currency;
    
    private String description;

    private String paymentToken;

    @NotNull    
    private String customerId;

    @NotNull
    private String priceId;
    
    @NotNull
    private String selectedPlan;

} 