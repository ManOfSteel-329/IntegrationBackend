package com.funnelsensai.core.dto.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @NotNull
    @Valid
    private UserRegistrationRequest registration;
    
    @NotNull
    @Valid
    private CreatePaymentMethodRequest payment;
}