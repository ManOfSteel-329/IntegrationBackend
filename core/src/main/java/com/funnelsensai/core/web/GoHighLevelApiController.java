package com.funnelsensai.core.web;

import com.funnelsensai.core.dto.opportunities.GetOpportunityResponse;
import com.funnelsensai.core.dto.opportunities.GetSearchOpportunityResponse;
import com.funnelsensai.core.service.SearchOpportunityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GoHighLevelApiController {

    private final SearchOpportunityService searchOpportunityService;

    public GoHighLevelApiController(SearchOpportunityService searchOpportunityService) {
        this.searchOpportunityService = searchOpportunityService;
    }

    @GetMapping("/highlevel/api/getopportunity")
    public GetOpportunityResponse getOpportunity() throws IOException {
        return searchOpportunityService.getOpportunityFromApi();
    }

    @GetMapping("/highlevel/api/searchopportunity")
    public GetSearchOpportunityResponse getSearchOpportunity() throws IOException {
        return searchOpportunityService.getSearchOpportunityFromApi();
    }

}
