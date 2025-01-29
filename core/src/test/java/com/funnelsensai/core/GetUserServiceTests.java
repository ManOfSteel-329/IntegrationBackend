package com.funnelsensai.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.dto.users.UserDTO;
import com.funnelsensai.core.service.GetUserService;
import okhttp3.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GetUserServiceTest {
    private GetUserService getUserService;

    @Mock
    private OkHttpClient mockClient;

    @Mock
    private Call mockCall;

    @Mock
    private Response mockResponse;

    @Mock
    private ResponseBody mockResponseBody;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getUserService = new GetUserService(mockClient, objectMapper);
    }

    @Test
    void fetchUserDTO_SuccessfulResponse_ReturnsUserDTO() throws IOException {
        // Arrange
        String apiResponse = """
                {
                    "id": "0IHuJvc2ofPAAA8GzTRi",
                    "name": "John Deo",
                    "firstName": "John",
                    "lastName": "Deo",
                    "email": "john@deo.com",
                    "phone": "+1 808-868-8888",
                    "permissions": {
                        "campaignsEnabled": true
                    },
                    "scopes": "campaigns.readonly",
                    "roles": {
                        "type": "account",
                        "role": "admin"
                    }
                }
                """;

        // Mock the request and response
        Request request = new Request.Builder().url("https://example.com").build();
        when(mockClient.newCall(any())).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn(apiResponse);

        // Act
        UserDTO result = getUserService.fetchUserDTO();

        // Assert
        assertNotNull(result);
        assertEquals("0IHuJvc2ofPAAA8GzTRi", result.getId());
        assertEquals("John Deo", result.getName());
        verify(mockClient, times(1)).newCall(any());
    }

    @Test
    void fetchUserDTO_UnsuccessfulResponse_ThrowsIOException() throws IOException {
        // Arrange
        when(mockClient.newCall(any())).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(false);
        when(mockResponse.code()).thenReturn(404);
        when(mockResponse.message()).thenReturn("Not Found");

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> getUserService.fetchUserDTO());
        assertEquals("Request failed: 404 Not Found", exception.getMessage());
        verify(mockClient, times(1)).newCall(any());
    }

}