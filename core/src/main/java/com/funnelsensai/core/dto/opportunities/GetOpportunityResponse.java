package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOpportunityResponse {

    @JsonProperty("opportunity")
    private Opportunity opportunity;

    public GetOpportunityResponse() {
    }

    public GetOpportunityResponse(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

}
