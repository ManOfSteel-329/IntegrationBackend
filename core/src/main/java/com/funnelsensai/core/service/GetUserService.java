package com.funnelsensai.core.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.dto.users.UserDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class GetUserService {

        private static final String API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582858/users/{userId}";
        private static final String API_VERSION = "2021-07-28";
        private static final String TOKEN = "9c48df2694a849b6089f9d0d3513efe";

        private OkHttpClient client = new OkHttpClient();
        private ObjectMapper objectMapper = new ObjectMapper();

    public GetUserService(OkHttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public UserDTO fetchUserDTO() throws IOException {
            UserDTO userDTO;
            Request request = new Request.Builder()
                    .url(API_URL)
                    .get()
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .addHeader("Version", API_VERSION)
                    .addHeader("Accept", "application/json")
                    .build();

            // Execute the request
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Request failed: " + response.code() + " " + response.message());
                }

                assert response.body() != null;
                userDTO= objectMapper.readValue(response.body().string(), UserDTO.class);
                return userDTO;
            }
        }
    }