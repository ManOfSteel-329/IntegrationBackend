package com.funnelsensai.core.dto.subscription;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubscriptionRequest {
    @NotNull
    private String customerId;
    @NotNull
    private String planName;
    @NotNull
    private String paymentMethodId;
    @NotNull
    private String companyName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}