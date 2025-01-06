package com.funnelsensai.core.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.funnelsensai.core.DTO.CalendarEvent;
import com.funnelsensai.core.DTO.CalendarEventsResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CalendarEventService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String BASE_URL = "https://stoplight.io/mocks/highlevel/integrations/39582850/calendars/events";

    public CalendarEventService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        ObjectMapper configuredMapper = objectMapper.copy();
        configuredMapper.registerModule(new JavaTimeModule());
        configuredMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(configuredMapper);

        this.restTemplate = restTemplateBuilder.build();
        this.restTemplate.getMessageConverters().add(0, converter);
        this.objectMapper = configuredMapper;
    }

    public List<CalendarEvent> fetchCalendarEvents(String bearerToken, String apiVersion, String locationId, String startTime,
                                                   String endTime, String calendarId, String groupId, String userId) {

        if (bearerToken == null || apiVersion == null || locationId == null || startTime == null || endTime == null ||
                bearerToken.isEmpty() || apiVersion.isEmpty() || locationId.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new IllegalArgumentException("Token, Version, Location Id, Start Time and End Time are required");
        }

        if (calendarId == null && groupId == null && userId == null) {
            throw new IllegalArgumentException("At least one of calendarId, groupId or userId is required");
        }

        assert calendarId != null;
        if (calendarId.isEmpty() && groupId.isEmpty() && userId.isEmpty()) {
            throw new IllegalArgumentException("At least one of calendarId, groupId or userId is required");
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

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.set("Version", apiVersion);
        headers.set("Accept", "application/json");

        ResponseEntity<CalendarEventsResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CalendarEventsResponse.class);

        return response.getBody() != null ? response.getBody().getEvents() : Collections.emptyList();
    }

}
