package com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FunnelDto {

    @JsonProperty("_id")
    private String funnelId;

    @JsonProperty("dateAdded")
    private String dateAdded;

    @JsonProperty("dateUpdated")
    private String dateUpdated;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("domainId")
    private String domainId;

    @JsonProperty("locationId")
    private String locationId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("orderFormVersion")
    private int orderFormVersion;

    @JsonProperty("originId")
    private String originId;

    @JsonProperty("steps")
    private List<FunnelStepDto> steps;

    @JsonProperty("type")
    private String type;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("faviconUrl")
    private String faviconUrl;

    @JsonProperty("globalSectionVersion")
    private int globalSectionVersion;

    @JsonProperty("globalSectionsPath")
    private String globalSectionsPath;

    @JsonProperty("globalSectionsUrl")
    private String globalSectionsUrl;

    @JsonProperty("isStoreActive")
    private boolean isStoreActive;

    @JsonProperty("trackingCodeBody")
    private String trackingCodeBody;

    @JsonProperty("trackingCodeHead")
    private String trackingCodeHead;

    @JsonProperty("url")
    private String url;

    public String getFunnelId() {
        return funnelId;
    }

    public void setFunnelId(String funnelId) {
        this.funnelId = funnelId;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderFormVersion() {
        return orderFormVersion;
    }

    public void setOrderFormVersion(int orderFormVersion) {
        this.orderFormVersion = orderFormVersion;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public List<FunnelStepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<FunnelStepDto> steps) {
        this.steps = steps;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    public int getGlobalSectionVersion() {
        return globalSectionVersion;
    }

    public void setGlobalSectionVersion(int globalSectionVersion) {
        this.globalSectionVersion = globalSectionVersion;
    }

    public String getGlobalSectionsPath() {
        return globalSectionsPath;
    }

    public void setGlobalSectionsPath(String globalSectionsPath) {
        this.globalSectionsPath = globalSectionsPath;
    }

    public String getGlobalSectionsUrl() {
        return globalSectionsUrl;
    }

    public void setGlobalSectionsUrl(String globalSectionsUrl) {
        this.globalSectionsUrl = globalSectionsUrl;
    }

    public boolean isStoreActive() {
        return isStoreActive;
    }

    public void setStoreActive(boolean storeActive) {
        isStoreActive = storeActive;
    }

    public String getTrackingCodeBody() {
        return trackingCodeBody;
    }

    public void setTrackingCodeBody(String trackingCodeBody) {
        this.trackingCodeBody = trackingCodeBody;
    }

    public String getTrackingCodeHead() {
        return trackingCodeHead;
    }

    public void setTrackingCodeHead(String trackingCodeHead) {
        this.trackingCodeHead = trackingCodeHead;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
