package com.funnelsensai.core.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;

@Configuration
public class OkHttpClientConfig {
	
	 @Bean
	    public OkHttpClient okHttpClient() {
	        return new OkHttpClient.Builder()
	                .connectTimeout(30, TimeUnit.SECONDS)
	                .readTimeout(30, TimeUnit.SECONDS)
	                .build();
	    }

}
