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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


@Service
public class CalendarEventService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String BASE_URL = "https://services.leadconnectorhq.com/calendars/events";;

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

    private String buildUrl(String locationId,
                            String startTime,
                            String endTime,
                            String calendarId,
                            String groupId,
                            String userId) {

        if (locationId == null || startTime == null || endTime == null) {
            throw new IllegalArgumentException("locationId, startTime, and endTime are required");
        }
        if (calendarId == null && groupId == null && userId == null) {
            throw new IllegalArgumentException("At least one of calendarId, groupId, or userId is required");
        }


        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(BASE_URL)
                  .append("?locationId=")
                  .append(locationId)
                  .append("&startTime=")
                  .append(startTime)
                  .append("&endTime=")
                  .append(endTime);

        if (calendarId != null) {
            urlBuilder.append("&calendarId=").append(calendarId);
        }
        if (groupId != null) {
            urlBuilder.append("&groupId=").append(groupId);
        }
        if (userId != null) {
            urlBuilder.append("&userId=").append(userId);
        }
        return urlBuilder.toString();
    }

    public List<CalendarEvent> fetchCalendarEvents(
            String bearerToken,
            String apiVersion,
            String locationId,
            String startTime,
            String endTime,
            String calendarId,
            String groupId,
            String userId) {

        if (apiVersion == null || bearerToken == null || locationId == null || startTime == null || endTime == null) {
            throw new IllegalArgumentException("Token, Version, Location Id, Start Time and End Time are required");
        }

        if (apiVersion.isEmpty() || bearerToken.isEmpty() || locationId.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new IllegalArgumentException("Token, Version, Location Id, Start Time and End Time are required");
        }

        String url = buildUrl(locationId, startTime, endTime, calendarId, groupId, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.set("Version", apiVersion);
        headers.set("Accept", "application/json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<CalendarEventsResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                CalendarEventsResponse.class);

        if (response.getBody() != null) {
            return response.getBody().getEvents();
        }
        return Collections.emptyList();
    }

}
