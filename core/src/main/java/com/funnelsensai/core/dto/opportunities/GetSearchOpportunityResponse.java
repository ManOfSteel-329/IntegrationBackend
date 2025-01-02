package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GetSearchOpportunityResponse {

    @JsonProperty("opportunities")
    private List<Opportunity> opportunities = new ArrayList<>();

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("aggregations")
    private Aggregation aggregation;

}
