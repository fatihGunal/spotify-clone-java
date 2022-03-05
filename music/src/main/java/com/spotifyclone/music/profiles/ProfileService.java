package com.spotifyclone.music.profiles;

import org.springframework.stereotype.Service;

@Service
public record ProfileService(ProfileRepository profileRepository) {

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
        profileRepository.save(profile);
    }
}
