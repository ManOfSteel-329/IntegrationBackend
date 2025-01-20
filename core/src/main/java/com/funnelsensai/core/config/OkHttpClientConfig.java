package com.funnelsensai.core.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpClientConfig {
    // Config class created to inject OkHttpClient as a Spring bean to run Mockito tests. Before i was having problem
    // to run tests successfully.

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build(); // Creates an OkHttpClient bean
    }
}
