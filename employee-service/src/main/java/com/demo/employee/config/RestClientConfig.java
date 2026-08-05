package com.demo.employee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${department.service.base-url}")
    private String departmentServiceBaseUrl;

    /** RestClient pre-configured with the department-service base URL. */
    @Bean
    public RestClient departmentRestClient() {
        return RestClient.builder()
                .baseUrl(departmentServiceBaseUrl)
                .build();
    }
}

