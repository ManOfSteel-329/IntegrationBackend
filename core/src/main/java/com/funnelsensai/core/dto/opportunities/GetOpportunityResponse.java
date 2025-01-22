package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetOpportunityResponse {

    @JsonProperty("opportunity")
    private Opportunity opportunity;

}
