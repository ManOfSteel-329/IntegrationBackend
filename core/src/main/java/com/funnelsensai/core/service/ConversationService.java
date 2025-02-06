package com.funnelsensai.core.service;

import com.funnelsensai.core.dto.conversations.ConversationDto;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

@Service
public class ConversationService {
    // Service class to manage GoHighLevel conversation fetch objects and Db
    // actions.

    private ConversationDto conversationDto; // global variable to be use in other methods if need it

    // To simulate Db
    Map<Integer, ConversationDto> conversations;

    private static final String API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582856/conversations/tDtDnQdgm2LXpyiqYvZ6";
    
    
    //@Value("${goHighLevel.mockToken}")
    private String API_KEY; // hard coded now but in the future this will be take it from the user from the
                            // security context

    private OkHttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ConversationService(OkHttpClient client) {
        // OkHttpClient client injected to be guarantee use in Mockito test
        this.client = client;
    }

    public ConversationDto fetchConversation() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .get()
                .addHeader("Authorization", "Bearer 123")// manually injected because of Mockito tests
                .addHeader("Version", "2021-04-15")
                .addHeader("Prefer", "code=200,dynamic=true")
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            // Deserialize response into a single DTO
            conversationDto = objectMapper.readValue(response.body().string(), ConversationDto.class);
            return conversationDto;
        }
    }

}
