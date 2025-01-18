package com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto;

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
public class FunnelResponseDto {
    //Response object return a Funnel object with count and traceid attributes

    @JsonProperty("funnels")
    private FunnelDto funnel;

    @JsonProperty("count")
    private int count;

    @JsonProperty("traceId")
    private String traceId;
}
