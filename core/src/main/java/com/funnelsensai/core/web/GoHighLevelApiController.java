package com.funnelsensai.core.web;

import com.funnelsensai.core.dto.SearchOpportunityRequest;
import com.funnelsensai.core.dto.SearchOpportunityResponse;
import com.funnelsensai.core.service.SearchOpportunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoHighLevelApiController {

    private final SearchOpportunityService searchOpportunityService;

    public GoHighLevelApiController(SearchOpportunityService searchOpportunityService) {
        this.searchOpportunityService = searchOpportunityService;
    }

    @GetMapping("/highlevel/api/searchopportunity")
    public ResponseEntity<SearchOpportunityResponse> getSearchOpportunity(@RequestParam SearchOpportunityRequest searchOppRequest) {
        SearchOpportunityResponse searchOpportunityResponse = searchOpportunityService.getSearchOpportunityFromGoHighLevelAPI(searchOppRequest);
        return ResponseEntity.ok(searchOpportunityResponse);
    }


}
