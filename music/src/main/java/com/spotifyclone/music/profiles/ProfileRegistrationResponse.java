package com.spotifyclone.music.profiles;

public record ProfileRegistrationResponse(String firstName,
                                          String lastName,
                                          String email,
                                          String userName) {
    // TODO: generate userName
}
