package com.funnelsensai.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.config.AppConfiguration;
import com.funnelsensai.core.dto.SearchOpportunityRequest;
import com.funnelsensai.core.dto.opportunities.GetOpportunityResponse;
import com.funnelsensai.core.dto.opportunities.GetSearchOpportunityResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Service
public class SearchOpportunityService {

    private static final String SEARCH_OPPORTUNITY_API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582852/opportunities/search?location_id=i2SpAtBVHSVea1sL6oah";
    private static final String SEARCH_OPPORTUNITY_API_KEY = "Bearer 123";

    public GetSearchOpportunityResponse getSearchOpportunityFromApi() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(SEARCH_OPPORTUNITY_API_URL)
                .get()
                .addHeader("Authorization", SEARCH_OPPORTUNITY_API_KEY)
                .addHeader("Version", "2021-07-28")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                return convertResponseToGetSearchOpportunityResponse(responseBody);
            } else {
                System.out.println("Request failed with status: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static GetSearchOpportunityResponse convertResponseToGetSearchOpportunityResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, GetSearchOpportunityResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AppConfiguration appConfig;

    public SearchOpportunityService (AppConfiguration appConfig) throws IOException {
        this.appConfig = appConfig;
    }

    public GetSearchOpportunityResponse getSearchOpportunityFromGoHighLevelAPI(SearchOpportunityRequest searchOppRequest) {

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

        ResponseEntity<GetSearchOpportunityResponse> searchOpportunityResponse = restTemplate1.getForEntity(searchOpportunityUri, GetSearchOpportunityResponse.class);
        return searchOpportunityResponse.getBody();
    }



}
