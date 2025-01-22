package com.funnelsensai.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.funnelsensai.core.dto.contacts.SearchContactsResponse;

import io.jsonwebtoken.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@ExtendWith(MockitoExtension.class)
class SearchContactsServiceTest {

	 @Mock
	    private OkHttpClient mockClient;

	    @Mock
	    private Call mockCall;

	    @Mock
	    private Response mockResponse;

	    @Mock
	    private ResponseBody mockResponseBody;

	    @InjectMocks
	    private SearchContactsService searchContactsService;

	    @BeforeEach
	    void setUp() {
	        searchContactsService = new SearchContactsService(mockClient);
	    }
	
	@Test
	void testSearchContactsService() throws IOException, java.io.IOException {
		
		//Arrange
		String jsonResponse = """
                {
                    "contacts": [{"id": "1", "firstNameLowerCase": "john"}],
                    "total": "1"
                }
                """;

        Mockito.when(mockClient.newCall(Mockito.any(Request.class))).thenReturn(mockCall);
        Mockito.when(mockCall.execute()).thenReturn(mockResponse);
        Mockito.when(mockResponse.isSuccessful()).thenReturn(true);
        Mockito.when(mockResponse.body()).thenReturn(mockResponseBody);
        Mockito.when(mockResponseBody.string()).thenReturn(jsonResponse);
		
		//Act
        SearchContactsResponse result = searchContactsService.postSearchContactsFromApi();
		
		//Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("1", result.getTotal());
        Assertions.assertEquals(1, result.getContacts().size());
        Assertions.assertEquals("john", result.getContacts().get(0).getFirstNameLowerCase());
        
        // Verify mock interactions
        Mockito.verify(mockClient).newCall(Mockito.any(Request.class));
        Mockito.verify(mockCall).execute();
	}

}
