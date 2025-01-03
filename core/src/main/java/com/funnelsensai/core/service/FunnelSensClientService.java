package com.funnelsensai.core.service;


import org.junit.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class FunnelSensClientService {
    private final String API_URL = "https://stoplight.io/mocks/highlevel/integrations/39582858/users/userId";
    private final String API_VERSION = "2021-07-28";
    private final String TOKEN = "Bearer 123";

    @Test
    public void getFunnelUser() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", TOKEN)
                .header("Version", API_VERSION)
                .header("Accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println(response.body());
        } else {
           throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }


    }
}

