package com.funnelsensai.core.dto.subscription;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponse {
    private Boolean requiresAction;
    private String clientSecret;
    private String error;
    private Boolean success;
    private String subscriptionId;
    private String status;
} 