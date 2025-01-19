package com.funnelsensai.core.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.dto.contacts.SearchContactsResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class SearchContactsService {

	public SearchContactsResponse postSearchContactsFromApi() {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create("{}", mediaType);

		Request request = new Request.Builder()
				.url("https://stoplight.io/mocks/highlevel/integrations/39582863/contacts/search")
				.post(body)
				.addHeader("Authorization", "Bearer 123")
				.addHeader("Version", "2021-07-28")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful() && response.body() != null) {
				String responseBody = response.body().string();
				ObjectMapper objectMapper = new ObjectMapper();
				
				System.out.println(response);
				System.out.println(responseBody);
				
				return objectMapper.readValue(responseBody, SearchContactsResponse.class);
			} else {
				System.err.println("Request failed: " + response.code() + " - " + response.message());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		return null; 
	}

}