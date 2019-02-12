package com.sammy.feginCustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.sammy")
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.sammy.api")
@EnableHystrix
public class FeginCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeginCustomerApplication.class, args);
    }


}

