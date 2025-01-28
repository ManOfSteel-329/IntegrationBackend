package mockitoTests;

import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelPagesCountDto;
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
public class FunnelListPagesCountConnTest {

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

        String validJson = "{\"count\": 10}";

        lenient().when(client.newCall(any())).thenReturn(call);
        lenient().when(call.execute()).thenReturn(response);
        lenient().when(response.isSuccessful()).thenReturn(true);
        lenient().when(response.body()).thenReturn(responseBody);
        lenient().when(responseBody.string()).thenReturn(validJson);
    }

    @Test
    void testFetchFunnelPagesCount_Success() throws IOException {
        FunnelPagesCountDto fetchedCount = funnelService.fetchFunnelPagesCount();

        assertNotNull(fetchedCount);
        assertEquals(10, fetchedCount.getCount());
        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchFunnelPagesCount_WhenApiCallFails() throws IOException {
        Response failedResponse = mock(Response.class);
        when(failedResponse.isSuccessful()).thenReturn(false);
        when(failedResponse.code()).thenReturn(500);
        when(call.execute()).thenReturn(failedResponse);

        assertThrows(IOException.class, () -> funnelService.fetchFunnelPagesCount());
        verify(client, times(1)).newCall(any(Request.class));
    }

    @Test
    void testFetchFunnelPagesCount_WithNetworkTimeout() throws IOException {
        when(call.execute()).thenThrow(new IOException("Network timeout"));
        assertThrows(IOException.class, () -> funnelService.fetchFunnelPagesCount());
    }

    @Test
    void testFetchFunnelPagesCount_WithInvalidJson() throws IOException {
        when(responseBody.string()).thenReturn("invalid json");
        assertThrows(Exception.class, () -> funnelService.fetchFunnelPagesCount());
    }

    @Test
    void testFetchFunnelPagesCount_WithEmptyResponse() throws IOException {
        when(responseBody.string()).thenReturn("{}");
        FunnelPagesCountDto fetchedCount = funnelService.fetchFunnelPagesCount();
        assertNotNull(fetchedCount);
        assertEquals(0, fetchedCount.getCount());
    }
}
