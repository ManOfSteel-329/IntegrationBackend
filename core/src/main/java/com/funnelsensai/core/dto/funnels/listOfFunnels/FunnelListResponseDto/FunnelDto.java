package com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@SuperBuilder
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
}
