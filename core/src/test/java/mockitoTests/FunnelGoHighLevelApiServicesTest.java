package mockitoTests;

import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListPagesDto;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto.FunnelResponseDto;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelPagesCountDto;
import com.funnelsensai.core.service.FunnelGoHighLevelApiServices;
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
public class FunnelGoHighLevelApiServicesTest {

    @Mock
    private OkHttpClient client; // Mock HTTP client

    @InjectMocks
    private FunnelGoHighLevelApiServices funnelService; // Inject the mocked client

    private Call call;
    private Response response;
    private ResponseBody responseBody;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        // Mock HTTP call, response, and body
        response = mock(Response.class);
        responseBody = mock(ResponseBody.class);
        call = mock(Call.class);

        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(responseBody);

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
    }

    /** Test Fetch Funnels List */
    @Test
    void testFetchFunnelsList_Success() throws IOException {
        // Mock API response as a single object, not an array
        when(responseBody.string()).thenReturn(
                "{ \"funnels\": { \"_id\": \"SkIDfu0S4m3NYQyvWHC6\", \"name\": \"Chaitanya Copy\" }, " +
                        "\"count\": 24, \"traceId\": \"03774d31-a57e-4b4f-95c7-315ce61969f1\" }"
        );

        FunnelResponseDto fetchedFunnels = funnelService.fetchFunnelsList();

        assertNotNull(fetchedFunnels);
        assertNotNull(fetchedFunnels.getFunnel());
        assertEquals("SkIDfu0S4m3NYQyvWHC6", fetchedFunnels.getFunnel().getFunnelId());
        assertEquals("Chaitanya Copy", fetchedFunnels.getFunnel().getName());
        assertEquals(24, fetchedFunnels.getCount());
        assertEquals("03774d31-a57e-4b4f-95c7-315ce61969f1", fetchedFunnels.getTraceId());

        verify(client, times(1)).newCall(any(Request.class));
    }


    /** Test Fetch Funnels List Pages */
    @Test
    void testFetchFunnelsListPages_Success() throws IOException {
        when(responseBody.string()).thenReturn("{\"pages\": []}");

        FunnelListPagesDto fetchedPages = funnelService.fetchFunnelsListPages();

        assertNotNull(fetchedPages);
        verify(client, times(1)).newCall(any(Request.class));
    }


    /** Test Fetch Funnel Pages Count */
    @Test
    void testFetchFunnelPagesCount_Success() throws IOException {
        when(responseBody.string()).thenReturn("{\"count\": 10}");

        FunnelPagesCountDto fetchedCount = funnelService.fetchFunnelPagesCount();

        assertNotNull(fetchedCount);
        verify(client, times(1)).newCall(any(Request.class));
    }

}
