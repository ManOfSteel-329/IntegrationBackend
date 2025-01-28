package com.funnelsensai.core.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListPagesDto;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelPagesCountDto;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto.FunnelResponseDto;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class FunnelGoHighLevelApiServices {
    // Service class to manage Funnel Objects fetch from GoHighLevelApi (Objects:
    // FunnelList, FunnelListPages, PagesCount)

    private FunnelListPagesDto funnelListPagesDto;
    private FunnelPagesCountDto funnelPagesCountDto;

    private static final String API_BASE_URL = "https://stoplight.io/mocks/highlevel/integrations/274944167/funnels/";
    private static final String FUNNEL_LIST_URL = "funnel/list?locationId=ojQjykmwNIU88vfsfzvH";
    private static final String FUNNEL_LIST_PAGES_URL = "page?funnelId=iucJ6TdFZiddhq9f6znh&limit=3&locationId=ojQjykmwNIU88vfsfzvH&offset=3";
    private static final String FUNNEL_LIST_PAGES_COUNT_URL = "page/count?funnelId=iucJ6TdFZiddhq9f6znh&locationId=ojQjykmwNIU88vfsfzvH";

    @Value("${goHighLevel.mockToken}")
    private String API_KEY; // hard coded now but in the future this will be take it from the user from the
                            // security context

    private OkHttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FunnelGoHighLevelApiServices(OkHttpClient client) {
        // OkHttpClient client injected to be guarantee use in Mockito test
        this.client = client;
    }

    public FunnelResponseDto fetchFunnelsList() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + FUNNEL_LIST_URL)
                .get()
                .addHeader("Authorization", "Bearer 123")
                .addHeader("Prefer", "code=200, dynamic=true")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            // Deserialize response into FunnelResponseDto
            return objectMapper.readValue(response.body().string(), FunnelResponseDto.class);
        }
    }

    public FunnelListPagesDto fetchFunnelsListPages() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + FUNNEL_LIST_PAGES_URL)
                .get()
                .addHeader("Authorization", "Bearer 123")
                .addHeader("Prefer", "code=200, dynamic=true")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            // Deserialize response into a single DTO
            funnelListPagesDto = objectMapper.readValue(response.body().string(), FunnelListPagesDto.class);
            return funnelListPagesDto;
        }
    }

    public FunnelPagesCountDto fetchFunnelPagesCount() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + FUNNEL_LIST_PAGES_COUNT_URL)
                .get()
                .addHeader("Authorization", "Bearer 123")
                .addHeader("Prefer", "code=200, dynamic=true")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }
            // Deserialize response into a single DTO
            funnelPagesCountDto = objectMapper.readValue(response.body().string(), FunnelPagesCountDto.class);
            return funnelPagesCountDto;
        }
    }

}
