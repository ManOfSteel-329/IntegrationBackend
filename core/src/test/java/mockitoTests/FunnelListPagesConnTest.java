package mockitoTests;

import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListPagesDto;
import com.funnelsensai.core.service.FunnelGoHighLevelApiServices;
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
public class FunnelListPagesConnTest {

    @Mock
    private OkHttpClient client;

    @InjectMocks
    private FunnelGoHighLevelApiServices funnelService;

    private Call call;
    private Response response;
    private ResponseBody responseBody;

    @BeforeEach
    void setUp() throws IOException {
        call = mock(Call.class);
        response = mock(Response.class);
        responseBody = mock(ResponseBody.class);

        String validJson = "{" +
                "\"_id\": \"pageList123\", " +
                "\"locationId\": \"loc001\", " +
                "\"funnelId\": \"funnel001\", " +
                "\"name\": \"Test Funnel\", " +
                "\"stepId\": \"step123\", " +
                "\"deleted\": \"false\", " +
                "\"updatedAt\": \"2024-01-01T12:00:00Z\" }";

        lenient().when(client.newCall(any())).thenReturn(call);
        lenient().when(call.execute()).thenReturn(response);
        lenient().when(response.isSuccessful()).thenReturn(true);
        lenient().when(response.body()).thenReturn(responseBody);
        lenient().when(responseBody.string()).thenReturn(validJson);
    }

    @Test
    void testFetchFunnelsListPages_Success() throws IOException {
        FunnelListPagesDto fetchedPages = funnelService.fetchFunnelsListPages();

        assertNotNull(fetchedPages);
        assertEquals("pageList123", fetchedPages.getPagesListId());
        assertEquals("loc001", fetchedPages.getLocationId());
        assertEquals("funnel001", fetchedPages.getFunnelId());
        assertEquals("Test Funnel", fetchedPages.getFunnelName());
        assertEquals("step123", fetchedPages.getStepId());
        assertEquals("false", fetchedPages.getDeleted());
        assertEquals("2024-01-01T12:00:00Z", fetchedPages.getUpdatedAt());

        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchFunnelsListPages_WhenApiCallFails() throws IOException {
        Response failedResponse = mock(Response.class);
        when(failedResponse.isSuccessful()).thenReturn(false);
        when(failedResponse.code()).thenReturn(500);
        when(call.execute()).thenReturn(failedResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsListPages());
        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchFunnelsListPages_WithNetworkTimeout() throws IOException {
        when(call.execute()).thenThrow(new IOException("Network timeout"));
        assertThrows(IOException.class, () -> funnelService.fetchFunnelsListPages());
    }

    @Test
    void testFetchFunnelsListPages_WithInvalidJson() throws IOException {
        when(responseBody.string()).thenReturn("invalid json");
        assertThrows(Exception.class, () -> funnelService.fetchFunnelsListPages());
    }

    @Test
    void testFetchFunnelsListPages_WithEmptyResponse() throws IOException {
        when(responseBody.string()).thenReturn("{}");
        FunnelListPagesDto fetchedPages = funnelService.fetchFunnelsListPages();
        assertNotNull(fetchedPages);
        assertNull(fetchedPages.getPagesListId());
    }

    @Test
    void testFetchFunnelsListPages_WithHttpError429() throws IOException {
        Response rateLimitResponse = mock(Response.class);
        when(rateLimitResponse.isSuccessful()).thenReturn(false);
        when(rateLimitResponse.code()).thenReturn(429);
        when(call.execute()).thenReturn(rateLimitResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsListPages());
    }

    @Test
    void testFetchFunnelsListPages_WithUnauthorizedAccess() throws IOException {
        Response unauthorizedResponse = mock(Response.class);
        when(unauthorizedResponse.isSuccessful()).thenReturn(false);
        when(unauthorizedResponse.code()).thenReturn(401);
        when(call.execute()).thenReturn(unauthorizedResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsListPages());
    }

    @Test
    void testFetchFunnelsListPages_WithServerOverload() throws IOException {
        Response serviceUnavailableResponse = mock(Response.class);
        when(serviceUnavailableResponse.isSuccessful()).thenReturn(false);
        when(serviceUnavailableResponse.code()).thenReturn(503);
        when(call.execute()).thenReturn(serviceUnavailableResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsListPages());
    }
}

