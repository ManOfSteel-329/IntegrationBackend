package com.funnelsensai.core.dto.payment;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentIntentResponse {
    @JsonProperty("clientSecret")
    private String clientSecret;
} 