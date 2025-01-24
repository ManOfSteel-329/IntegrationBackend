package com.funnelsensai.core.dto.funnels.listOfFunnels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class FunnelListPagesDto {
    @JsonProperty("_id")
    private String pagesListId;

    private String locationId;
    private String funnelId;

    @JsonProperty("name")
    private String funnelName;

    private String stepId;
    private String deleted;
    private String updatedAt;

    public String getPagesListId() {
        return pagesListId;
    }

    public void setPagesListId(String pagesListId) {
        this.pagesListId = pagesListId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getFunnelId() {
        return funnelId;
    }

    public void setFunnelId(String funnelId) {
        this.funnelId = funnelId;
    }

    public String getFunnelName() {
        return funnelName;
    }

    public void setFunnelName(String funnelName) {
        this.funnelName = funnelName;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
