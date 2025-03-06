package com.funnelsensai.core.dto.subscription;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubscriptionRequest {
    @NotNull
    private String customerId;
    @NotNull
    private String paymentMethodId;
    @NotNull
    private String planName;
    @NotNull
    private String companyName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    
    // Account holder's name - used for user creation in database
    @NotNull
    private String accountFirstName;
    @NotNull
    private String accountLastName;
    
    // Cardholder's name - used for Stripe billing
    @NotNull
    private String cardholderFirstName;
    @NotNull
    private String cardholderLastName;

    @Override
    public String toString() {
        return "CreateSubscriptionRequest{" +
                "customerId='" + customerId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", planName='" + planName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountFirstName='" + accountFirstName + '\'' +
                ", accountLastName='" + accountLastName + '\'' +
                ", cardholderFirstName='" + cardholderFirstName + '\'' +
                ", cardholderLastName='" + cardholderLastName + '\'' +
                '}';
    }

}