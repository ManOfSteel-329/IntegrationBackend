package com.funnelsensai.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.funnelsensai.core.dto.CalendarEvent.CalendarEvent;
import com.funnelsensai.core.dto.CalendarEvent.CalendarEventResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class CalendarEventService {

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String BASE_URL = "https://stoplight.io/mocks/highlevel/integrations/39582850/calendars/events";

    public CalendarEventService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        ObjectMapper configuredMapper = objectMapper.copy();
        configuredMapper.registerModule(new JavaTimeModule());
        configuredMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        configuredMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(configuredMapper);
        this.objectMapper = configuredMapper;
    }

    public List<CalendarEvent> fetchCalendarEvents(String bearerToken, String apiVersion, String locationId, String startTime,
                                                   String endTime, String calendarId, String groupId, String userId) throws IOException {

        if (bearerToken == null || apiVersion == null || locationId == null || startTime == null || endTime == null ||
                bearerToken.isEmpty() || apiVersion.isEmpty() || locationId.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new IllegalArgumentException("Token, Version, Location Id, Start Time and End Time are required");
        }

        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("locationId", locationId)
                .queryParam("startTime", startTime)
                .queryParam("endTime", endTime)
                .queryParamIfPresent("calendarId", Optional.ofNullable(calendarId))
                .queryParamIfPresent("groupId", Optional.ofNullable(groupId))
                .queryParamIfPresent("userId", Optional.ofNullable(userId))
                .build()
                .toUri();

        Request request = new Request.Builder()
                .url(uri.toString())
                .header("Authorization", "Bearer " + bearerToken)
                .header("Version", apiVersion)
                .header("Accept", "application/json")
                .build();

        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code: " + response);
        }

        String responseBody = response.body() != null ? response.body().string() : null;

        System.out.println(responseBody);

        if (responseBody == null || responseBody.isEmpty()) {
            return Collections.emptyList();
        }

        CalendarEventResponse calendarEventsResponse = objectMapper.readValue(responseBody, CalendarEventResponse.class);
        return calendarEventsResponse.getEvents();
    }
}


