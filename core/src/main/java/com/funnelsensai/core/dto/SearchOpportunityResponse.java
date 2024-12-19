package com.funnelsensai.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;

import java.util.List;

public class SearchOpportunityResponse {

    @JsonProperty("opportunities")
    private List<Opportunity> opportunities;

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("aggregations")
    private Aggregation aggregation;

}
