package com.spotifyclone.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.spotifyclone.clients"
)
public class MusicApplication {
    //this is test comment
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }
}
