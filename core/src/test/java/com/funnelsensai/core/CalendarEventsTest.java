package com.funnelsensai.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.funnelsensai.core.dto.CalendarEvent.CalendarEvent;
import com.funnelsensai.core.service.CalendarEventService;
import okhttp3.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CalendarEventsTest {

    @Mock
    private OkHttpClient mockHttpClient;

    @Mock
    private Call mockCall;

    private CalendarEventService calendarEventService;
    private ObjectMapper objectMapper;
    private AutoCloseable closeable;

    // Test data
    private static final String mockBaseUrl = "https://stoplight.io/mocks/highlevel/integrations/39582850/calendars/events";
    private static final String authToken = "9c48df2694a849b6089f9d0d3513efe";
    private static final String apiVersion = "2021-04-15";
    private static final String locationId = "0007BWpSzSwfiuSl0tR2";
    private static final String startTime = "1680373800000";
    private static final String endTime = "1680978599999";
    private static final String calendarId = "BqTwX8QFwXzpegMve9EQ";

    private String mockResponseBody;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        calendarEventService = new CalendarEventService(mockHttpClient, objectMapper);

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
                        "users": ["YlWd2wuCAZQzh2cH1fVZ", "9NkT25Vor1v4aQatFsv2"],
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
    @DisplayName("Should correctly map JSON response to CalendarEventDTO")
    public void testCalendarEventDTOMapping() throws IOException {
        ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                mockResponseBody
        );

        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("Ok")
                .body(responseBody)
                .build();

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                authToken,
                apiVersion,
                locationId,
                startTime,
                endTime,
                calendarId,
                null,
                null
        );

        assertNotNull(events);
        assertEquals(1, events.size());

        CalendarEvent event = events.get(0);

        assertEquals("ocQHyuzHvysMo5N5VsXc", event.getId());
        assertEquals("https://meet.google.com/yqp-gogr-wve", event.getAddress());
        assertEquals("Appointment with GHL Dev team", event.getTitle());
        assertEquals("BqTwX8QFwXzpegMve9EQ", event.getCalendarId());
        assertEquals("0007BWpSzSwfiuSl0tR2", event.getLocationId());
        assertEquals("9NkT25Vor1v4aQatFsv2", event.getContactId());
        assertEquals("9NkT25Vor1v4aQatFsv2", event.getGroupId());
        assertEquals("confirmed", event.getAppointmentStatus());
        assertEquals("YlWd2wuCAZQzh2cH1fVZ", event.getAssignedUserId());
        assertEquals(List.of("YlWd2wuCAZQzh2cH1fVZ", "9NkT25Vor1v4aQatFsv2"), event.getUsers());
        assertEquals("Some dummy note", event.getNotes());
        assertEquals("true", event.isRecurring());
        assertEquals("RRULE:FREQ=DAILY;INTERVAL=1;COUNT=5", event.getRrule());
        assertNotNull(event.getStartTime());
        assertNotNull(event.getEndTime());
        assertNotNull(event.getDateAdded());
        assertNotNull(event.getDateUpdated());
        assertEquals(List.of("string"), event.getAssignedResources());
        assertEquals("ocWd2wuBGAQzh2cH1fSZ", event.getMasterEventId());

        assertEquals(
                LocalDateTime.parse("2023-09-25T16:00:00+05:30", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                event.getStartTime()
        );


    }

    @Test
    @DisplayName("Should mock a successful endpoint call")
    public void testSuccessfulGetCalendarEvents() throws Exception {
        ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                mockResponseBody
        );

        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build();

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                authToken,
                apiVersion,
                locationId,
                startTime,
                endTime,
                calendarId,
                null,
                null
        );

        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals("ocQHyuzHvysMo5N5VsXc", events.get(0).getId());
    }

    @Test
    @DisplayName("Should handle null response body")
    public void testNullResponseBody() throws IOException {
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(MediaType.parse("application/json"), ""))
                .build();

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                authToken,
                apiVersion,
                locationId,
                startTime,
                endTime,
                calendarId,
                null,
                null
        );
        assertTrue(events.isEmpty());
    }

    @Test
    @DisplayName("Should handle server error response")
    public void testServerError() throws IOException {
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(500)
                .message("Internal Server Error")
                .body(ResponseBody.create(MediaType.parse("text/plain"), "Server Error"))
                .build();

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        assertThrows(IOException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        authToken,
                        apiVersion,
                        locationId,
                        startTime,
                        endTime,
                        calendarId,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should handle forbidden response")
    public void testForbiddenResponse() throws IOException {
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(403)
                .message("Forbidden")
                .body(ResponseBody.create(MediaType.parse("text/plain"), "Forbidden"))
                .build();

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        assertThrows(IOException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        authToken,
                        apiVersion,
                        locationId,
                        startTime,
                        endTime,
                        calendarId,
                        null,
                        null
                )
        );
    }

    @Test
    @DisplayName("Should return empty list for empty events array")
    public void testEmptyEventsArray() throws IOException {
        String emptyEventsResponse = """
                {
                    "events": []
                }
                """;

        ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json"),
                emptyEventsResponse
        );

        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build();

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                authToken,
                apiVersion,
                locationId,
                startTime,
                endTime,
                calendarId,
                null,
                null
        );

        assertNotNull(events);
        assertTrue(events.isEmpty());
    }

    @Test
    @DisplayName("Should throw exception when auth token is missing")
    public void testMissingAuthToken() {
        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        null,
                        apiVersion,
                        locationId,
                        startTime,
                        endTime,
                        calendarId,
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
                        authToken,
                        null,
                        locationId,
                        startTime,
                        endTime,
                        calendarId,
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
                        authToken,
                        apiVersion,
                        null,
                        startTime,
                        endTime,
                        calendarId,
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
                        authToken,
                        apiVersion,
                        locationId,
                        null,
                        endTime,
                        calendarId,
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
                        authToken,
                        apiVersion,
                        locationId,
                        startTime,
                        null,
                        calendarId,
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
                        apiVersion,
                        locationId,
                        startTime,
                        endTime,
                        calendarId,
                        null,
                        null
                )
        );

        assertThrows(IllegalArgumentException.class, () ->
                calendarEventService.fetchCalendarEvents(
                        authToken,
                        "",
                        locationId,
                        startTime,
                        endTime,
                        calendarId,
                        null,
                        null
                )
        );
    }
}