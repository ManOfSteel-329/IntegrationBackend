package mockitoTests;

import com.funnelsensai.core.dto.conversations.ConversationDto;
import com.funnelsensai.core.service.ConversationService;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConversationApiEndPointConnTest {

    @Mock
    private OkHttpClient client; // Mocking HTTP client

    @InjectMocks
    private ConversationService conversationService;

    private Call call;
    private Response response;
    private ResponseBody responseBody;

    @BeforeEach
    void setUp() throws IOException {
        // Create fresh mocks for each test
        call = mock(Call.class);
        response = mock(Response.class);
        responseBody = mock(ResponseBody.class);

        // Setup default successful response
        String validJson = "{" +
                "\"contactId\":\"string\"," +
                "\"locationId\":\"string\"," +
                "\"deleted\":true," +
                "\"inbox\":true," +
                "\"type\":0," +
                "\"unreadCount\":0," +
                "\"assignedTo\":\"string\"," +
                "\"id\":\"string\"," +
                "\"starred\":\"true\"" +
                "}";

        // Setup the complete chain in correct order
        lenient().when(client.newCall(any())).thenReturn(call);
        lenient().when(call.execute()).thenReturn(response);
        lenient().when(response.isSuccessful()).thenReturn(true);
        lenient().when(response.body()).thenReturn(responseBody);
        lenient().when(responseBody.string()).thenReturn(validJson);
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

    @Test
    void testFetchConversation_WhenApiCallFails() throws IOException {
        // Reset the response for this specific test
        Response failedResponse = mock(Response.class);
        when(failedResponse.isSuccessful()).thenReturn(false);
        when(failedResponse.code()).thenReturn(500);
        when(call.execute()).thenReturn(failedResponse);

        assertThrows(IOException.class, () -> {
            conversationService.fetchConversation();
        });
        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchConversation_WithNetworkTimeout() throws IOException {
        // Mock network timeout
        when(call.execute()).thenThrow(new IOException("Network timeout"));

        assertThrows(IOException.class, () -> {
            conversationService.fetchConversation();
        });
    }

    @Test
    void testFetchConversation_WithInvalidJson() throws IOException {
        // Mock invalid JSON response
        when(responseBody.string()).thenReturn("invalid json");

        assertThrows(Exception.class, () -> {
            conversationService.fetchConversation();
        });
    }

    @Test
    void testFetchConversation_WithEmptyResponse() throws IOException {
        // Override just the response body content
        when(responseBody.string()).thenReturn("{}");

        ConversationDto fetchedConversation = conversationService.fetchConversation();
        assertNotNull(fetchedConversation);
        assertNull(fetchedConversation.getContactId());
    }

    @Test
    void testFetchConversation_WithPartialData() throws IOException {
        // Test with only some fields present
        when(responseBody.string()).thenReturn("{\"contactId\":\"string\",\"locationId\":\"string\"}");

        ConversationDto fetchedConversation = conversationService.fetchConversation();
        assertNotNull(fetchedConversation);
        assertEquals("string", fetchedConversation.getContactId());
        assertEquals("string", fetchedConversation.getLocationId());
        assertNull(fetchedConversation.getAssignedTo());
    }

    @Test
    void testFetchConversation_WithExtraFields() throws IOException {
        // Test with additional unknown fields in JSON
        when(responseBody.string()).thenReturn(
                "{\"contactId\":\"string\",\"extraField\":\"value\",\"locationId\":\"string\"}");

        ConversationDto fetchedConversation = conversationService.fetchConversation();
        assertNotNull(fetchedConversation);
        assertEquals("string", fetchedConversation.getContactId());
        assertEquals("string", fetchedConversation.getLocationId());
    }

    @Test
    void testFetchConversation_WithHttpError429() throws IOException {
        // Test rate limiting response
        Response rateLimitResponse = mock(Response.class);
        when(rateLimitResponse.isSuccessful()).thenReturn(false);
        when(rateLimitResponse.code()).thenReturn(429);
        when(call.execute()).thenReturn(rateLimitResponse);

        assertThrows(IOException.class, () -> {
            conversationService.fetchConversation();
        });
    }

}
