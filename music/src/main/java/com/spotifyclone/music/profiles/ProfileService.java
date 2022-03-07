package com.spotifyclone.music.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public record ProfileService(ProfileRepository profileRepository) {


    public void registerProfile(final ProfileRegistrationRequest request) {
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
        System.out.println(fraudResponse);
    }
    private FraudResponse askFraud(final Profile profile) {

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();
        FraudResponse fraudResponse = webClient
                .get()
                .uri("/api/v1/fraud-check/{profileId}", profile.getId())
                .retrieve()
                .bodyToMono(FraudResponse.class)
                .block();
        return fraudResponse;
    }
}
