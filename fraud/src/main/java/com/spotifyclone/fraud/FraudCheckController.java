package com.spotifyclone.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @Autowired
    public FraudCheckController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping(path = "{profileId}")
    public ResponseEntity<FraudCheckResponse> checkFraud(@PathVariable("profileId") Long profileId) {
        return new ResponseEntity<>(fraudCheckService.isFraudulentProfile(profileId), HttpStatus.OK);
    }

    // Experimenting with webflux Mono/Flux..
//    @PostMapping(path = "/webflux/{profileId}")
//    public Mono<FraudCheckResponse> isFraudsterMono(@PathVariable("profileId") Long profileId) throws InterruptedException {
//        return fraudCheckService.isFraudulentProfileMono(profileId);
//    }
}
