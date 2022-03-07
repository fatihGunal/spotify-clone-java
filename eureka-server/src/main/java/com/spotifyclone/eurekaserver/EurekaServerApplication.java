package com.spotifyclone.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    // replace eureka in the future with kubernetes
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
