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
public class FunnelStepDto {
    @JsonProperty("id")
    private String step_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("originId")
    private String originId;

    @JsonProperty("pages")
    private List<String> pages;

    @JsonProperty("products")
    private List<String> products;

    @JsonProperty("sequence")
    private int sequence;

    @JsonProperty("type")
    private String type;

    @JsonProperty("url")
    private String url;
}
