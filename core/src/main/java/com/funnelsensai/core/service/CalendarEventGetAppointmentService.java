package com.funnelsensai.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.funnelsensai.core.dto.calendarEventGetAppointment.CalendarEventGetAppointmentResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CalendarEventGetAppointmentService {
    private static final String CALENDAR_EVENTS_GET_APPOINTMENT_API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582850/calendars/events/appointments/";
    private static final String CALENDAR_EVENTS_GET_APPOINTMENT_API_KEY = "Bearer 123";
    private final OkHttpClient client;

    public CalendarEventGetAppointmentService(OkHttpClient client) {
        this.client = client != null ? client : new OkHttpClient();
    }

    public CalendarEventGetAppointmentService() {
        this(null);
    }

    public CalendarEventGetAppointmentResponse calendarEventGetAppointmentFromApi(String id) throws IOException {
        Request request = new Request.Builder()
                .url(CALENDAR_EVENTS_GET_APPOINTMENT_API_URL + id)
                .get()
                .addHeader("Authorization", CALENDAR_EVENTS_GET_APPOINTMENT_API_KEY)
                .addHeader("Version", "2021-04-15")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            String responseBody = response.body().string();

            ObjectMapper objectMapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(responseBody, CalendarEventGetAppointmentResponse.class);
        }
    }
}
