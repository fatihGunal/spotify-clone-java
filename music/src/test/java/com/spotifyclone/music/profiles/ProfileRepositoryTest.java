package com.spotifyclone.music.profiles;

import com.spotifyclone.music.subscription.Subscription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void saveProfile() {
        Profile profile = Profile.builder()
                .firstName("Fatih")
                .lastName("Gunal")
                .email("fatihgunal2018@gmail.com")
                .userName("fatihGunal.dev")
                .build();

        profileRepository.save(profile);
    }

    @Test
    public void saveProfileWithSubscription() {
        Subscription subscription = Subscription.builder()
                .type("Premium")
                .pricing(new BigDecimal("19.99"))
                .build();

        Profile profile = Profile.builder()
                .firstName("Fatih")
                .lastName("Gunal")
                .email("fatihgunal2019@gmail.com")
                .userName("fatihGunal.devv")
                .subscription(subscription)
                .build();

        profileRepository.save(profile);
    }

    @Test
    public void deleteAllProfilesShouldNotDeleteSubscription() {
        profileRepository.deleteAll();
    }

}