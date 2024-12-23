package com.funnelsensai.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.funnelsensai.core.DTO.CalendarEvent;
import com.funnelsensai.core.DTO.CalendarEventsResponse;
import com.funnelsensai.core.service.CalendarEventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalendarEventsTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    private CalendarEventService calendarEventService;
    private ObjectMapper objectMapper;
    private AutoCloseable closeable;

    private static final String BASE_URL = "https://services.leadconnectorhq.com/calendars/events";
    private static final String AUTH_TOKEN = "9c48df2694a849b6089f9d0d3513efe";
    private static final String API_VERSION = "2021-04-15";
    private static final String LOCATION_ID = "0007BWpSzSwfiuSl0tR2";
    private static final String START_TIME = "1680373800000";
    private static final String END_TIME = "1680978599999";
    private static final String CALENDAR_ID = "BqTwX8QFwXzpegMve9EQ";
    private static final String GROUP_ID = "ocQHyuzHvysMo5N5VsXc";
    private static final String USER_ID = "CVokAlI8fgw4WYWoCtQz";

    private String mockResponseBody;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        calendarEventService = new CalendarEventService(restTemplateBuilder, objectMapper);

        mockResponseBody = """
                {
                    "events": [{
                        "id": "ocQHyuzHvysMo5N5VsXc",
                        "address": "https://meet.google.com/yqp-gogr-wve",
                        "title": "Appointment with GHL Dev team",
                        "calendarId": "BqTwX8QFwXzpegMve9EQ",
                        "locationId": "0007BWpSzSwfiuSl0tR2",
                        "contactId": "9NkT25Vor1v4aQatFsv2",
                        "groupId": "9NkT25Vor1v4aQatFsv2",
                        "appointmentStatus": "confirmed",
                        "assignedUserId": "YlWd2wuCAZQzh2cH1fVZ",
                        "users": [
                              "YlWd2wuCAZQzh2cH1fVZ",
                              "9NkT25Vor1v4aQatFsv2"
                        ],
                        "notes": "Some dummy note",
                        "isRecurring": "true",
                        "rrule": "RRULE:FREQ=DAILY;INTERVAL=1;COUNT=5",
                        "startTime": "2023-09-25T16:00:00+05:30",
                        "endTime": "2023-09-25T16:00:00+05:30",
                        "dateAdded": "2023-09-25T16:00:00+05:30",
                        "dateUpdated": "2023-09-25T16:00:00+05:30",
                        "assignedResources": ["string"],
                        "masterEventId": "ocWd2wuBGAQzh2cH1fSZ"
                    }]
                }
                """;
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Should mock a successful endpoint call")
    public void testSuccessfulGetCalendarEvents() throws Exception {

        CalendarEventsResponse mockResponse = objectMapper.readValue(mockResponseBody, CalendarEventsResponse.class);
        ResponseEntity<CalendarEventsResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        )).thenReturn(responseEntity);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                AUTH_TOKEN,
                API_VERSION,
                LOCATION_ID,
                START_TIME,
                END_TIME,
                CALENDAR_ID,
                null,
                null
        );

        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals("ocQHyuzHvysMo5N5VsXc", events.get(0).getId());
        verify(restTemplate).exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        );
    }

    @Test
    @DisplayName("Should handle null response body")
    public void testNullResponseBody() {
        ResponseEntity<CalendarEventsResponse> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        )).thenReturn(responseEntity);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                AUTH_TOKEN,
                API_VERSION,
                LOCATION_ID,
                START_TIME,
                END_TIME,
                CALENDAR_ID,
                null,
                null
        );
        assertTrue(events.isEmpty());
    }

    @Test
    @DisplayName("Should handle server error response")
    public void testServerError() {
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        )).thenThrow(new org.springframework.web.client.HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(org.springframework.web.client.HttpServerErrorException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should handle forbidden response")
    public void testForbiddenResponse() {
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        )).thenThrow(new org.springframework.web.client.HttpClientErrorException(HttpStatus.FORBIDDEN));

        assertThrows(org.springframework.web.client.HttpClientErrorException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when auth token is missing")
    public void testMissingAuthToken() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        null,
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when API version is missing")
    public void testMissingApiVersion() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        null,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when location ID is missing")
    public void testMissingLocationId() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        API_VERSION,
                        null,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when start time is missing")
    public void testMissingStartTime() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        API_VERSION,
                        LOCATION_ID,
                        null,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when end time is missing")
    public void testMissingEndTime() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        null,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }


    @Test
    @DisplayName("Should mock a failed endpoint call due to an invalid Authorization token")
    public void testInvalidAuthToken() throws Exception {

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        )).thenThrow(new org.springframework.web.client.HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThrows(org.springframework.web.client.HttpClientErrorException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        "invalid-token",
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should return a 200 after returning an empty events array")
    public void testEmptyEventsArray() throws Exception {
        String emptyEventsResponse = """
                {
                    "events": []
                }
                """;

        CalendarEventsResponse mockResponse = objectMapper.readValue(emptyEventsResponse, CalendarEventsResponse.class);
        ResponseEntity<CalendarEventsResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        )).thenReturn(responseEntity);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                AUTH_TOKEN,
                API_VERSION,
                LOCATION_ID,
                START_TIME,
                END_TIME,
                CALENDAR_ID,
                null,
                null
        );

        assertNotNull(events);
        assertTrue(events.isEmpty());
        verify(restTemplate).exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(CalendarEventsResponse.class)
        );
    }

    @Test
    @DisplayName("Should throw exception when API version is null")
    public void testNullApiVersion() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        null,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when API version is empty")
    public void testEmptyApiVersion() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        "",
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when required parameters are null")
    public void testNullRequiredParams() {

        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        null,
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );

        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        null,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when required parameters are empty")
    public void testEmptyRequiredParams() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        "",
                        API_VERSION,
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );

        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        AUTH_TOKEN,
                        "",
                        LOCATION_ID,
                        START_TIME,
                        END_TIME,
                        CALENDAR_ID,
                        null,
                        null
                )
        );
    }
}