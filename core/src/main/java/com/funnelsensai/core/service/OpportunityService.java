package com.funnelsensai.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.config.AppConfiguration;
import com.funnelsensai.core.dto.opportunities.GetOpportunityResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpportunityService {

    private final AppConfiguration appConfig;

    public OpportunityService(AppConfiguration appConfig) throws IOException {
        this.appConfig = appConfig;
    }

    private static final String OPPORTUNITY_API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582852/opportunities/yWQobCRIhRguQtD2llvk";
    private static final String OPPORTUNITY_API_KEY = "Bearer 123";

    public GetOpportunityResponse getOpportunityFromApi () throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(OPPORTUNITY_API_URL)
                .get()
                .addHeader("Authorization", OPPORTUNITY_API_KEY)
                .addHeader("Version", "2021-07-28")
                .addHeader("Prefer", "code=200,dynamic=true")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                return convertResponseToGetOpportunityResponse(responseBody);
        } else {
                System.out.println("Request failed with status: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static GetOpportunityResponse convertResponseToGetOpportunityResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, GetOpportunityResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public GetOpportunityResponse getOpportunityFromGoHighLevelAPI(OpportunityRequest oppRequest) {
//
//        RestTemplate restTemplate1 = new RestTemplate();
//
//        URI opportunityUri = UriComponentsBuilder.fromHttpUrl(appConfig.getApiUrlBase()+appConfig.getApiUrlOpportunityEndpoint())
//                .queryParam("id", oppRequest.getId())
//                .build()
//                .toUri();
//
//        ResponseEntity<GetOpportunityResponse> opportunityResponse = restTemplate1.getForEntity(opportunityUri, GetOpportunityResponse.class);
//        return opportunityResponse.getBody();
//    }





}
