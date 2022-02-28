package com.spotifyclone.profiles.profile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/profiles")
public record ProfileController(ProfileService profileService) {
    
    public void registerProfile(@RequestBody ProfileRegistrationRequest profileRegistrationRequest) {
        log.info("new profile registration {}", profileRegistrationRequest);
        profileService.registerProfile(profileRegistrationRequest);
    }
}
