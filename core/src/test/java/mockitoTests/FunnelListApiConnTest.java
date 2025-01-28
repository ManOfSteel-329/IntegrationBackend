package mockitoTests;

import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto.FunnelResponseDto;
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
public class FunnelListApiConnTest {

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
                "\"funnels\": { \"_id\": \"SkIDfu0S4m3NYQyvWHC6\", \"name\": \"Chaitanya Copy\" }, " +
                "\"count\": 24, \"traceId\": \"03774d31-a57e-4b4f-95c7-315ce61969f1\" }";

        lenient().when(client.newCall(any())).thenReturn(call);
        lenient().when(call.execute()).thenReturn(response);
        lenient().when(response.isSuccessful()).thenReturn(true);
        lenient().when(response.body()).thenReturn(responseBody);
        lenient().when(responseBody.string()).thenReturn(validJson);
    }

    @Test
    void testFetchFunnelsList_Success() throws IOException {
        FunnelResponseDto fetchedFunnels = funnelService.fetchFunnelsList();

        assertNotNull(fetchedFunnels);
        assertNotNull(fetchedFunnels.getFunnel());
        assertEquals("SkIDfu0S4m3NYQyvWHC6", fetchedFunnels.getFunnel().getFunnelId());
        assertEquals("Chaitanya Copy", fetchedFunnels.getFunnel().getName());
        assertEquals(24, fetchedFunnels.getCount());
        assertEquals("03774d31-a57e-4b4f-95c7-315ce61969f1", fetchedFunnels.getTraceId());

        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchFunnelsList_WhenApiCallFails() throws IOException {
        Response failedResponse = mock(Response.class);
        when(failedResponse.isSuccessful()).thenReturn(false);
        when(failedResponse.code()).thenReturn(500);
        when(call.execute()).thenReturn(failedResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchFunnelsList_WithNetworkTimeout() throws IOException {
        when(call.execute()).thenThrow(new IOException("Network timeout"));
        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithInvalidJson() throws IOException {
        when(responseBody.string()).thenReturn("invalid json");
        assertThrows(Exception.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithEmptyResponse() throws IOException {
        when(responseBody.string()).thenReturn("{}");
        FunnelResponseDto fetchedFunnels = funnelService.fetchFunnelsList();
        assertNotNull(fetchedFunnels);
        assertNull(fetchedFunnels.getFunnel());
    }

    @Test
    void testFetchFunnelsList_WithPartialData() throws IOException {
        when(responseBody.string()).thenReturn("{\"funnels\": { \"_id\": \"SkIDfu0S4m3NYQyvWHC6\" }}");
        FunnelResponseDto fetchedFunnels = funnelService.fetchFunnelsList();
        assertNotNull(fetchedFunnels);
        assertNotNull(fetchedFunnels.getFunnel());
        assertEquals("SkIDfu0S4m3NYQyvWHC6", fetchedFunnels.getFunnel().getFunnelId());
        assertNull(fetchedFunnels.getFunnel().getName());
    }

    @Test
    void testFetchFunnelsList_WithExtraFields() throws IOException {
        when(responseBody.string()).thenReturn("{" +
                "\"funnels\": { \"_id\": \"SkIDfu0S4m3NYQyvWHC6\", \"name\": \"Chaitanya Copy\", \"extraField\": \"value\" }, " +
                "\"count\": 24, \"traceId\": \"03774d31-a57e-4b4f-95c7-315ce61969f1\" }");
        FunnelResponseDto fetchedFunnels = funnelService.fetchFunnelsList();
        assertNotNull(fetchedFunnels);
        assertEquals("SkIDfu0S4m3NYQyvWHC6", fetchedFunnels.getFunnel().getFunnelId());
        assertEquals("Chaitanya Copy", fetchedFunnels.getFunnel().getName());
    }

    @Test
    void testFetchFunnelsList_WithHttpError429() throws IOException {
        Response rateLimitResponse = mock(Response.class);
        when(rateLimitResponse.isSuccessful()).thenReturn(false);
        when(rateLimitResponse.code()).thenReturn(429);
        when(call.execute()).thenReturn(rateLimitResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithNullResponseBody() throws IOException {
        when(response.body()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithUnauthorizedAccess() throws IOException {
        Response unauthorizedResponse = mock(Response.class);
        when(unauthorizedResponse.isSuccessful()).thenReturn(false);
        when(unauthorizedResponse.code()).thenReturn(401);
        when(call.execute()).thenReturn(unauthorizedResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithForbiddenAccess() throws IOException {
        Response forbiddenResponse = mock(Response.class);
        when(forbiddenResponse.isSuccessful()).thenReturn(false);
        when(forbiddenResponse.code()).thenReturn(403);
        when(call.execute()).thenReturn(forbiddenResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithNotFound() throws IOException {
        Response notFoundResponse = mock(Response.class);
        when(notFoundResponse.isSuccessful()).thenReturn(false);
        when(notFoundResponse.code()).thenReturn(404);
        when(call.execute()).thenReturn(notFoundResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithServerOverload() throws IOException {
        Response serviceUnavailableResponse = mock(Response.class);
        when(serviceUnavailableResponse.isSuccessful()).thenReturn(false);
        when(serviceUnavailableResponse.code()).thenReturn(503);
        when(call.execute()).thenReturn(serviceUnavailableResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithDelayedResponse() throws IOException {
        when(call.execute()).thenAnswer(invocation -> {
            Thread.sleep(5000); // Simulating delay
            return response;
        });

        assertDoesNotThrow(() -> funnelService.fetchFunnelsList());
    }

    @Test
    void testFetchFunnelsList_WithUnexpectedJsonStructure() throws IOException {
        when(responseBody.string()).thenReturn("{\"unexpectedField\":\"unexpectedValue\"}");

        FunnelResponseDto fetchedFunnels = funnelService.fetchFunnelsList();
        assertNotNull(fetchedFunnels);
        assertNull(fetchedFunnels.getFunnel()); // Ensures funnel object is null
    }

}
