package com.funnelsensai.core.web.request;


import java.util.HashMap;
import java.util.Map;

public class PaymentRequest {
    private Long amount;
    private String currency;
    private String description;
    private Map<String, String> metadata;

    public PaymentRequest() {

    }

    public PaymentRequest(Long amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long amount;
        private String currency;
        private String description;
        private Map<String, String> metadata;

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder addMetadata(String key, String value) {
            if (this.metadata == null) {
                this.metadata = new HashMap<>();
            }
            this.metadata.put(key, value);
            return this;
        }

        public PaymentRequest build() {
            PaymentRequest request = new PaymentRequest();
            request.setAmount(amount);
            request.setCurrency(currency);
            request.setDescription(description);
            request.setMetadata(metadata);
            return request;
        }
    }
}
