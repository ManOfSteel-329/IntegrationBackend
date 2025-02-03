package com.funnelsensai.core.service;

import com.funnelsensai.core.dto.appointmentsForContact.AppointmentsForContact;
import com.funnelsensai.core.dto.appointmentsForContact.AppointmentsForContactResponse;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAppointmentsForContactServiceTest {
    @Mock
    private OkHttpClient mockClient;

    @Mock
    private Call mockCall;

    private GetAppointmentsForContactService service;

    private static final String EXPECTED_URL = "https://stoplight.io/mocks/highlevel/integrations/39582863/contacts/sx6wyHhbFdRXh302LLNR/appointments";
    private static final String EXPECTED_AUTH = "Bearer 123";

    @BeforeEach
    void setUp() {
        service = new GetAppointmentsForContactService(mockClient);
    }

    @Test
    void getAppointmentsForContactFromApi_WhenSuccessful_ShouldReturnResponse() throws IOException {
        // Given
        String contactId = "sx6wyHhbFdRXh302LLNR";
        String jsonResponse = """
            {
                "events": [
                    {
                        "id": "123",
                        "calendarId": "cal123",
                        "status": "confirmed",
                        "title": "Test Appointment",
                        "appointmentStatus": "scheduled",
                        "assignedUserId": "user123",
                        "notes": "Test notes",
                        "startTime": "2024-01-26T10:00:00Z",
                        "endTime": "2024-01-26T11:00:00Z",
                        "address": "123 Test St",
                        "locationId": "loc123",
                        "contactId": "contact123",
                        "groupId": "group123",
                        "users": ["user1", "user2"],
                        "dateAdded": "2024-01-25T10:00:00Z",
                        "dateUpdated": "2024-01-25T10:00:00Z",
                        "assignedResources": ["resource1", "resource2"]
                    }
                ]
            }
            """;

        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url(EXPECTED_URL).build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(MediaType.get("application/json"), jsonResponse))
                .build();

        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        AppointmentsForContactResponse response = service.getAppointmentsForContactFromApi(contactId);

        verify(mockClient).newCall(argThat(request ->
                request.url().toString().equals(EXPECTED_URL) &&
                        request.header("Authorization").equals(EXPECTED_AUTH) &&
                        request.header("Version").equals("2021-07-28") &&
                        request.header("Accept").equals("application/json")
        ));

        assertThat(response).isNotNull();
        assertThat(response.getAppointments())
                .isNotNull()
                .hasSize(1);

        AppointmentsForContact appointment = response.getAppointments().get(0);
        assertThat(appointment.getId()).isEqualTo("123");
        assertThat(appointment.getStatus()).isEqualTo("confirmed");
        assertThat(appointment.getTitle()).isEqualTo("Test Appointment");
    }

    @Test
    void getAppointmentsForContactFromApi_WhenApiReturnsError_ShouldThrowIOException() throws IOException {
        String contactId = "sx6wyHhbFdRXh302LLNR";
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url(EXPECTED_URL).build())
                .protocol(Protocol.HTTP_1_1)
                .code(404)
                .message("Not Found")
                .body(ResponseBody.create(MediaType.get("application/json"), ""))
                .build();

        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        IOException exception = assertThrows(IOException.class,
                () -> service.getAppointmentsForContactFromApi(contactId)
        );
        assertThat(exception.getMessage()).contains("Unexpected code");
    }

    @Test
    void getAppointmentsForContactFromApi_WhenNetworkError_ShouldThrowIOException() throws IOException {
        String contactId = "sx6wyHhbFdRXh302LLNR";
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new IOException("Network error"));

        assertThrows(IOException.class,
                () -> service.getAppointmentsForContactFromApi(contactId)
        );
    }
}