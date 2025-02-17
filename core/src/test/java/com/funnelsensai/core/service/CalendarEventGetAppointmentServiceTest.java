package com.funnelsensai.core.service;

import com.funnelsensai.core.dto.calendarEventGetAppointment.CalendarEventGetAppointmentResponse;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalendarEventGetAppointmentServiceTest {
    @Mock
    private OkHttpClient mockClient;
    @Mock
    private Call mockCall;
    private CalendarEventGetAppointmentService service;
    private static final String EXPECTED_AUTH = "Bearer 123";

    @BeforeEach
    void setUp() {
        service = new CalendarEventGetAppointmentService(mockClient);
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
    }

    @Test
    void shouldReturnAppointment_WhenApiCallIsSuccessful() throws IOException {
        // Given
        String eventId = "ocQHyuzHvysMo5N5VsXc";
        String jsonResponse = """
                {
                   "event": {
                     "id": "ocQHyuzHvysMo5N5VsXc",
                     "title": "Appointment with GHL Dev team",
                     "startTime": "2023-09-25T16:00:00+05:30",
                     "endTime": "2023-09-25T16:00:00+05:30"
                   }
                }
                """;
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("https://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(MediaType.get("application/json"), jsonResponse))
                .build();

        when(mockCall.execute()).thenReturn(mockResponse);

        // When
        CalendarEventGetAppointmentResponse response = service.calendarEventGetAppointmentFromApi(eventId);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getEvent()).isNotNull();
        assertThat(response.getEvent().getId()).isEqualTo("ocQHyuzHvysMo5N5VsXc");
        assertThat(response.getEvent().getTitle()).isEqualTo("Appointment with GHL Dev team");

        // Verify correct headers and URL
        verify(mockClient).newCall(argThat(request -> {
            return request.url().toString().endsWith(eventId) &&
                    request.header("Authorization").equals(EXPECTED_AUTH) &&
                    request.header("Version").equals("2021-04-15") &&
                    request.header("Accept").equals("application/json");
        }));
    }

    @Test
    void shouldThrowIOException_WhenApiReturns404() throws IOException {
        // Given
        String eventId = "nonexistent";
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("https://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(404)
                .message("Not Found")
                .body(ResponseBody.create(MediaType.get("application/json"), ""))
                .build();

        when(mockCall.execute()).thenReturn(mockResponse);

        // When/Then
        IOException exception = assertThrows(IOException.class,
                () -> service.calendarEventGetAppointmentFromApi(eventId)
        );
        assertThat(exception).hasMessageContaining("Unexpected code");
    }

    @Test
    void shouldThrowIOException_WhenApiReturns500() throws IOException {
        // Given
        String eventId = "server-error";
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("https://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(500)
                .message("Internal Server Error")
                .body(ResponseBody.create(MediaType.get("application/json"), ""))
                .build();

        when(mockCall.execute()).thenReturn(mockResponse);

        // When/Then
        IOException exception = assertThrows(IOException.class,
                () -> service.calendarEventGetAppointmentFromApi(eventId)
        );
        assertThat(exception).hasMessageContaining("Unexpected code");
    }

    @Test
    void shouldThrowIOException_WhenNetworkError() throws IOException {
        // Given
        String eventId = "network-error";
        when(mockCall.execute()).thenThrow(new IOException("Network error"));

        // When/Then
        IOException exception = assertThrows(IOException.class,
                () -> service.calendarEventGetAppointmentFromApi(eventId)
        );
        assertThat(exception).hasMessageContaining("Network error");
    }

    @Test
    void shouldThrowIOException_WhenInvalidJsonReturned() throws IOException {
        // Given
        String eventId = "invalid-json";
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("https://test.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(MediaType.get("application/json"), "Invalid JSON"))
                .build();

        when(mockCall.execute()).thenReturn(mockResponse);

        // When/Then
        assertThrows(IOException.class,
                () -> service.calendarEventGetAppointmentFromApi(eventId)
        );
    }
}

