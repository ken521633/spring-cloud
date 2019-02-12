package com.sammy.feginService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FeginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeginServiceApplication.class, args);
    }

}

