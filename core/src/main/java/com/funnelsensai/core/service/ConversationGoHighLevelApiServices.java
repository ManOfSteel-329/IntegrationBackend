package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Conversation;
import com.funnelsensai.core.dto.conversations.ConversationDto;
import com.funnelsensai.core.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;


@Service
public class ConversationGoHighLevelApiServices {

    private ConversationDto conversationDto; //global variable to be use in other methods if need it
    private final ConversationRepository conversationRepository;

    private static final String API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582856/conversations/tDtDnQdgm2LXpyiqYvZ6";
    private static final String API_KEY = "Bearer 123";  // Replace with actual API key

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ConversationGoHighLevelApiServices(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public ConversationDto fetchConversation() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .get()
                .addHeader("Authorization", API_KEY)
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

    public ConversationDto saveConversation() throws IOException {
        // Setting the entity with the Dto values
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);

        conversation.setContactId(conversationDto.getContactId());
        conversation.setLocationId(conversationDto.getLocationId());
        conversation.setDeleted(conversationDto.getDeleted());
        conversation.setInbox(conversationDto.getInbox());
        conversation.setType(conversationDto.getType());
        conversation.setUnreadCount(conversationDto.getUnreadCount());
        conversation.setAssignedTo(conversationDto.getAssignedTo());
        conversation.setConversationId(conversationDto.getConversationId());
        conversation.setStarred(conversationDto.getStarred());

        conversationRepository.save(conversation);

        return conversationDto;

    }
}
