package com.funnelsensai.core.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.funnelsensai.core.dto.appointmentsForContact.AppointmentsForContactResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GetAppointmentsForContactService {
    private static final String GETAPPOINTMENT_API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582863/contacts/";
    private static final String GETAPPOINTMENT_API_KEY = "Bearer 123";

    private final OkHttpClient client;

    public GetAppointmentsForContactService(OkHttpClient client) {
        this.client = client != null ? client : new OkHttpClient();
    }

    public AppointmentsForContactResponse getAppointmentsForContactFromApi(String id) throws IOException {
        Request request = new Request.Builder()
                .url(GETAPPOINTMENT_API_URL + id + "/appointments")
                .get()
                .addHeader("Authorization", GETAPPOINTMENT_API_KEY)
                .addHeader("Version", "2021-07-28")
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
            return objectMapper.readValue(responseBody, AppointmentsForContactResponse.class);
        }
    }
}
