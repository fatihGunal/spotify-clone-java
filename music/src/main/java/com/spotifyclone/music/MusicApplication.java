package com.spotifyclone.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MusicApplication {
    //this is test comment
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }
}
