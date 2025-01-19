package mockitoTests.conversationApi;

import com.funnelsensai.core.domain.Conversation;
import com.funnelsensai.core.dto.conversations.ConversationDto;
import com.funnelsensai.core.repository.ConversationRepository;
import com.funnelsensai.core.service.ConversationGoHighLevelApiServices;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito for JUnit 5
public class ConversationServiceTest {

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private OkHttpClient client; // Mocking HTTP client

    @InjectMocks
    private ConversationGoHighLevelApiServices conversationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchConversation() throws IOException {
        // Mock HTTP response
        Response response = mock(Response.class);
        ResponseBody responseBody = mock(ResponseBody.class);

        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn("{\"contactId\":\"12345\", \"locationId\":\"67890\", \"deleted\":false, \"inbox\":true, \"type\":1.0, \"unreadCount\":3.0, \"assignedTo\":\"user@example.com\", \"id\":\"conv-001\", \"starred\":\"true\"}");

        Call call = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);

        // Call service method
        ConversationDto result = conversationService.fetchConversation();

        // Validate response
        assertNotNull(result);
        assertEquals("12345", result.getContactId());
        assertEquals("conv-001", result.getConversationId());

        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testSaveConversation() throws IOException {
        // Mock DTO
        ConversationDto conversationDto = new ConversationDto();
        conversationDto.setContactId("12345");
        conversationDto.setLocationId("67890");
        conversationDto.setDeleted(false);
        conversationDto.setInbox(true);
        conversationDto.setType(1.0f);
        conversationDto.setUnreadCount(3.0f);
        conversationDto.setAssignedTo("user@example.com");
        conversationDto.setConversationId("conv-001");
        conversationDto.setStarred("true");

        // Mock entity saving
        Conversation conversation = new Conversation();
        when(conversationRepository.save(any(Conversation.class))).thenReturn(conversation);

        // Call save method
        ConversationDto savedDto = conversationService.saveConversation();

        // Validate the saved DTO
        assertNotNull(savedDto);
        verify(conversationRepository, times(2)).save(any(Conversation.class));
    }
}
