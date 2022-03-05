package com.spotifyclone.music.profiles;

public record ProfileRegistrationRequest(String firstName,
        String lastName,
        String email,
        String userName,
        String birthDate) {
    // TODO: generate userName
}
