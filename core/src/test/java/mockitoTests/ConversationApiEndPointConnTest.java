package mockitoTests;

import com.funnelsensai.core.dto.conversations.ConversationDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConversationApiEndPointConnTest {


    @Mock
    private OkHttpClient client; // Mocking HTTP client

    @InjectMocks
    private ConversationGoHighLevelApiServices conversationService;

    private Call call;
    private Response response;
    private ResponseBody responseBody;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        // HTTP mocking here so it applies to all tests
        response = mock(Response.class);
        responseBody = mock(ResponseBody.class);
        call = mock(Call.class);

        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn(
                "{\"contactId\":\"string\", \"locationId\":\"string\", \"deleted\":true, \"inbox\":true, \"type\":0, \"unreadCount\":0, \"assignedTo\":\"string\", \"id\":\"string\", \"starred\":\"true\"}"
        );

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
    }


    @Test
    void testFetchConversation() throws IOException {
        ConversationDto fetchedConversation = conversationService.fetchConversation();

        assertNotNull(fetchedConversation);
        assertEquals(fetchedConversation.getInbox(), true);
        verify(client, times(1)).newCall(any(Request.class));
    }
    @Test
    void testFetchConversation_CorrectDtoMapping() throws IOException {
        ConversationDto fetchedConversation = conversationService.fetchConversation();

        assertNotNull(fetchedConversation);
        assertEquals("string", fetchedConversation.getContactId());
        assertEquals("string", fetchedConversation.getLocationId());
        assertTrue(fetchedConversation.getDeleted());
        assertTrue(fetchedConversation.getInbox());
        assertEquals(0, fetchedConversation.getType());
        assertEquals(0, fetchedConversation.getUnreadCount());
        assertEquals("string", fetchedConversation.getAssignedTo());
        assertEquals("string", fetchedConversation.getConversationId());
        assertEquals("true", fetchedConversation.getStarred());

        verify(client, times(1)).newCall(any(Request.class));
    }

}
