package com.zain.robot.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RobotFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotFrontendApplication.class, args);
    }

    @Bean("genericRestTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
