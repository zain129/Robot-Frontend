package com.zain.robot.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RobotFrontendApplication {

    @Value("${robot.backend.root_url}")
    private String robotBackendRootUrl;

    public static void main(String[] args) {
        SpringApplication.run(RobotFrontendApplication.class, args);
    }

    @Bean("robotBackendWebClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(robotBackendRootUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
