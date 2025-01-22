package com.funnelsensai.core.web;

import com.funnelsensai.core.dto.opportunities.GetOpportunityResponse;
import com.funnelsensai.core.dto.opportunities.GetSearchOpportunityResponse;
import com.funnelsensai.core.service.OpportunityService;
import com.funnelsensai.core.service.SearchOpportunityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GoHighLevelApiController {

    private final OpportunityService opportunityService;
    private final SearchOpportunityService searchOpportunityService;

    public GoHighLevelApiController(OpportunityService opportunityService, SearchOpportunityService searchOpportunityService) {
        this.opportunityService = opportunityService;
        this.searchOpportunityService = searchOpportunityService;
    }

    @GetMapping("/highlevel/api/getopportunity")
    public GetOpportunityResponse getOpportunity() throws IOException {
        return opportunityService.getOpportunityFromApi();
    }

    @GetMapping("/highlevel/api/searchopportunity")
    public GetSearchOpportunityResponse getSearchOpportunity() throws IOException {
        return searchOpportunityService.getSearchOpportunityFromApi();
    }

}
