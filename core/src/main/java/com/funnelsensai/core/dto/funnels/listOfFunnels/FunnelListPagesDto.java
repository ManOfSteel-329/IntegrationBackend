package com.funnelsensai.core.dto.funnels.listOfFunnels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@SuperBuilder
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

}
