package com.spotifyclone.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {

    @GetMapping(path = "api/v1/fraud-check/{profileId}")
    public ResponseEntity<FraudCheckResponse> checkFraud(@PathVariable("profileId") Long profileId);
}
