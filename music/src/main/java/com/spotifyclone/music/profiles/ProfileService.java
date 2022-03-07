package com.spotifyclone.music.profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
@Slf4j
public record ProfileService(ProfileRepository profileRepository) {


    public void registerProfile(final ProfileRegistrationResponse request) {
        Profile profile = Profile.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // TODO: Check if email valid
        // TODO: check if email not taken
        // TODO: store customer in db
        profileRepository.saveAndFlush(profile);
        FraudResponse fraudResponse = askFraud(profile);
        log.info("Do something with fraudResponse {}", fraudResponse);
    }

    @LoadBalanced
    private FraudResponse askFraud(final Profile profile) {

        // FRAUD as baseUrl is because we use Eureka service discovery, kinda cool!
        WebClient webClient = WebClient.builder().baseUrl("http://FRAUD:8081").build();

        FraudResponse fraudResponse = webClient
                .get()
                .uri("/api/v1/fraud-check/{profileId}", profile.getId())
                .retrieve()
                .bodyToMono(FraudResponse.class)
                .block();

        return fraudResponse;
    }
}
