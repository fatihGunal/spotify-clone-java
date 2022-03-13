package com.spotifyclone.music.profiles;

import com.spotifyclone.clients.fraud.FraudCheckResponse;
import com.spotifyclone.clients.fraud.FraudClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final WebClient.Builder webClient;
    private final FraudClient fraudClient;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, WebClient.Builder webClient, FraudClient fraudClient) {
        this.profileRepository = profileRepository;
        this.webClient = webClient;
        this.fraudClient = fraudClient;
    }

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
        FraudResponse fraudResponse = askFraudWithFraudClient(profile);
        log.info("Do something with fraudResponse {}", fraudResponse);
    }

    private FraudResponse askFraudWithWebclient(final Profile profile) {
        // CLIENT ALTERNATIVE 1: WebClient
        // FRAUD as baseUrl -> because we use Eureka service discovery, kinda cool!
        // Later replacing Eureka with kubertes
        FraudResponse fraudResponse = webClient
                .baseUrl("http://FRAUD")
                .build()
                .get()
                .uri("/api/v1/fraud-check/{profileId}", profile.getId())
                .retrieve()
                .bodyToMono(FraudResponse.class)
                .block();

        return fraudResponse;
    }

    private FraudResponse askFraudWithFraudClient(final Profile profile) {
        // CLIENT ALTERNATIVE 2: FraudClient with the use of OpenFeign
        // FRAUD as baseUrl -> because we use Eureka service discovery, kinda cool!
        // Later replacing Eureka with kubertes
        ResponseEntity<FraudCheckResponse> fraudResponse = fraudClient.checkFraud(Long.valueOf(profile.getId()));
        return FraudResponse.builder()
                .isFraudster(Objects.requireNonNull(fraudResponse.getBody()).isFraudster())
                .build();
    }
}
