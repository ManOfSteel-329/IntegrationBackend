package com.funnelsensai.core.service;

import java.io.IOException;

import com.funnelsensai.core.dto.contacts.SearchContactsResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchContactsService {

	public SearchContactsResponse getSearchContactsFromApi() {
		
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				  .url("https://stoplight.io/mocks/highlevel/integrations/39582863/contacts/search")
				  .get()
				  .addHeader("Authorization", "Bearer 123")
				  .addHeader("Version", "2021-07-28")
				  .addHeader("Content-Type", "application/json")
				  .addHeader("Accept", "application/json")
				  .build();

				try {
					Response response = client.newCall(request).execute();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
		return null;
	}

}
