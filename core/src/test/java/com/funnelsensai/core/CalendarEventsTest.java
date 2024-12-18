package com.funnelsensai.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.minidev.json.JSONValue.isValidJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalendarEventsTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    private AutoCloseable closeable;
    private ObjectMapper objectMapper;
    public String mockResponseBody;

    private static final String BASE_URL = "https://services.leadconnectorhq.com/calendars/events";
    private static final String AUTH_TOKEN = "test-token-123";
    private static final String API_VERSION = "2021-04-15";
    private static final String LOCATION_ID = "0007BWpSzSwfiuSl0tR2";
    private static final String START_TIME = "1680373800000";
    private static final String END_TIME = "1680978599999";

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
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
                }                }
                """;
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    private HttpRequest buildRequest(Map<String, String> queryParams) {

        StringBuilder urlBuilder =  new StringBuilder(BASE_URL + "?");
        queryParams.forEach((key, value) -> urlBuilder.append(key).append("=").append(value).append("&"));

        return HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.substring(0, urlBuilder.length() - 1)))
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .header("Version", API_VERSION)
                .header("Accept", "application/json")
                .GET()
                .build();
    }

    private boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

@Test
@DisplayName("Should mock a successful endpoint call")
    public void testSuccessfulGetCalendarEvents() throws Exception {

        Map<String, String> queryParams = Map.of(
                "locationId", LOCATION_ID,
                "startTime", START_TIME,
                "endTime", END_TIME
        );

        HttpRequest expectedRequest = buildRequest(queryParams);

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(mockResponseBody);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        HttpResponse<String> response = httpClient.send(expectedRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(isValidJson(response.body()));
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

@Test
@DisplayName("Should mock a failed endpoint call due to a missing required parameter")
    public void testMissingRequiredParams() throws Exception {

        Map<String, String> queryParams = Map.of(
                "locationId", LOCATION_ID,
                "startTime", START_TIME
        );

        HttpRequest invalidRequest = buildRequest(queryParams);

        when(httpResponse.statusCode()).thenReturn(400);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        HttpResponse<String> response = httpClient.send(invalidRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(400, response.statusCode());
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }


    @Test
    @DisplayName("Should mock a failed endpoint call due to an invalid Authorization token")
    public void testInvalidAuthToken() throws Exception {
        Map<String, String> queryParams = Map.of(
                "locationId", LOCATION_ID,
                "startTime", START_TIME,
                "endTime", END_TIME
        );
        HttpRequest requestWithInvalidToken = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?locationId=" + LOCATION_ID +
                        "&startTime=" + START_TIME +
                        "&endTime=" + END_TIME))
                .header("Authorization", "Bearer invalid-token")
                .header("Version", API_VERSION)
                .GET()
                .build();

        when(httpResponse.statusCode()).thenReturn(401);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        HttpResponse<String> response = httpClient.send(requestWithInvalidToken, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        assertEquals(401, response.statusCode());
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
