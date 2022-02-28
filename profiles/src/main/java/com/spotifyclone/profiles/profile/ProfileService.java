package com.spotifyclone.profiles.profile;

import org.springframework.stereotype.Service;

@Service
public record ProfileService() {

    public void registerProfile(ProfileRegistrationRequest request) {
        Profile profile = Profile.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .birthDate(request.birthDate())
                .email(request.email())
                .build();
        // TODO: Check if email valid
        // TODO: check if email not taken
        // TODO: store customer in db
    }
}
