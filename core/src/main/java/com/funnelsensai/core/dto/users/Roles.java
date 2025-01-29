package com.funnelsensai.core.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Roles {
    @JsonProperty("type")
    private String type;
    @JsonProperty("role")
    private String role;

    @JsonProperty("locationIds")
    private List<String> locationIds;

    @JsonProperty("restrictSubAccount")
    private boolean restrictSubAccount;

    public Roles() {
    }

    public Roles(String type, String role, List<String> locationIds, boolean restrictSubAccount) {
        this.type = type;
        this.role = role;
        this.locationIds = locationIds;
        this.restrictSubAccount = restrictSubAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<String> locationIds) {
        this.locationIds = locationIds;
    }

    public boolean isRestrictSubAccount() {
        return restrictSubAccount;
    }

    public void setRestrictSubAccount(boolean restrictSubAccount) {
        this.restrictSubAccount = restrictSubAccount;
    }


    @Override
    public String toString() {
        return "Roles{" +
                "type='" + type + '\'' +
                ", role='" + role + '\'' +
                ", locationIds=" + locationIds +
                ", restrictSubAccount=" + restrictSubAccount +
                '}';
    }
}

