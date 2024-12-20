package com.funnelsensai.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.DTO.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@Service
public class CalendarEventService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String BASE_URL = "https://services.leadconnectorhq.com/calendars/events";
    private final RestTemplateBuilder restTemplateBuilder;
    private final String API_VERSION = "2021-04-15";


    public CalendarEventService(RestTemplateBuilder restTemplate, ObjectMapper objectMapper, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
        this.restTemplateBuilder = restTemplateBuilder;
    }


    private String buildRequest(String bearerToken,
                                     String locationId,
                                     LocalDateTime startTime,
                                     LocalDateTime endTime,
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
        urlBuilder.append(BASE_URL).append("?locationId=").append(locationId).append("&startTime=").append(startTime).append("&endTime=").append(endTime);

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





//    public List<CalendarEvent> fetchCalendarEvents() {
//        return null;
//    }

}
