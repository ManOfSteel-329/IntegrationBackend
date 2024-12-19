package com.funnelsensai.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${gohighlevel.urls.base}")
    private String apiUrlBase;

    @Value("${gohighlevel.urls.searchopportunity}")
    private String apiUrlSearchOpportunityEndpoint;

    public String getApiUrlBase() {
        return apiUrlBase;
    }

    public String getApiUrlSearchOpportunityEndpoint() {
        return apiUrlSearchOpportunityEndpoint;
    }

}
