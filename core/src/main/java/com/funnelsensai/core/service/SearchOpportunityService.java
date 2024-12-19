package com.funnelsensai.core.service;

import com.funnelsensai.core.config.AppConfiguration;
import com.funnelsensai.core.dto.SearchOpportunityRequest;
import com.funnelsensai.core.dto.SearchOpportunityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class SearchOpportunityService {

    private AppConfiguration appConfig;

    public SearchOpportunityService (AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public SearchOpportunityResponse getSearchOpportunityFromGoHighLevelAPI(SearchOpportunityRequest searchOppRequest) {

        RestTemplate restTemplate1 = new RestTemplate();

        URI searchOpportunityUri = UriComponentsBuilder.fromHttpUrl(appConfig.getApiUrlBase()+appConfig.getApiUrlSearchOpportunityEndpoint())
                .queryParam("location_id", searchOppRequest.getLocation_id())
                .queryParam("assigned_to", searchOppRequest.getAssigned_to())
                .queryParam("campaignId", searchOppRequest.getCampaignId())
                .queryParam("contact_id", searchOppRequest.getContact_id())
                .queryParam("country", searchOppRequest.getCountry())
                .queryParam("date", searchOppRequest.getDate())
                .queryParam("endDate", searchOppRequest.getEndDate())
                .queryParam("getCalendarEvents", searchOppRequest.isGetCalendarEvents())
                .queryParam("getNotes", searchOppRequest.isGetNotes())
                .queryParam("getTasks", searchOppRequest.isGetTasks())
                .queryParam("id", searchOppRequest.getId())
                .queryParam("limit", searchOppRequest.getLimit())
                .queryParam("order", searchOppRequest.getOrder())
                .queryParam("page", searchOppRequest.getPage())
                .queryParam("pipeline_id", searchOppRequest.getPipeline_id())
                .queryParam("pipeline_stage_id", searchOppRequest.getPipeline_stage_id())
                .queryParam("q", searchOppRequest.getQ())
                .queryParam("startAfter", searchOppRequest.getStartAfter())
                .queryParam("startAfterId", searchOppRequest.getStartAfterId())
                .queryParam("status", searchOppRequest.getStatus())
                .build()
                .toUri();

        ResponseEntity<SearchOpportunityResponse> searchOpportunityResponse = restTemplate1.getForEntity(searchOpportunityUri, SearchOpportunityResponse.class);
        return searchOpportunityResponse.getBody();
    }



}
