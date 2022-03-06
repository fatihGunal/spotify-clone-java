package com.spotifyclone.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudCheckController {

    private FraudCheckService fraudCheckService;

    @Autowired
    public FraudCheckController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @PostMapping(path = "{profileId}")
    public ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable("profileId") Long profileId) {
        FraudCheckResponse fraudCheckResponse =
                new FraudCheckResponse(fraudCheckService.isFraudulentProfile(profileId));
        return new ResponseEntity<>(fraudCheckResponse, HttpStatus.OK);
    }

    // Experimenting with webflux Mono/Flux..
    @PostMapping(path = "/webflux/{profileId}")
    public Mono<FraudCheckResponse> isFraudsterMono(@PathVariable("profileId") Long profileId) throws InterruptedException {
        return fraudCheckService.isFraudulentProfileMono(profileId);
    }
}
