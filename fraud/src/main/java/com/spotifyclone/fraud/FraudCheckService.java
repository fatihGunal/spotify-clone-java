package com.spotifyclone.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Autowired
    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public FraudCheckResponse isFraudulentProfile(final Long profileId) {

        FraudCheckHistory fraudCheckHistory = fraudCheckHistoryRepository
                .getFraudCheckHistoryByProfileId(profileId);

        if (fraudCheckHistory == null) {
            saveFraud(profileId);
            return new FraudCheckResponse(false);
        }

        return new FraudCheckResponse(fraudCheckHistory.getIsFraudster());
    }

    private void saveFraud(final Long profileId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .profileId(profileId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }


    // this webflux approach doesn't go in depth
//    public Mono<FraudCheckResponse> isFraudulentProfileMono(final Long profileId) {
//
//        FraudCheckHistory fraudCheckHistory = fraudCheckHistoryRepository
//                .getFraudCheckHistoryByProfileId(profileId);
//
//        if (fraudCheckHistory == null) {
//            saveFraud(profileId);
//            return Mono.just(new FraudCheckResponse(false));
//        }
//
//        return Mono.just(new FraudCheckResponse(fraudCheckHistory.getIsFraudster()));
//    }
}
