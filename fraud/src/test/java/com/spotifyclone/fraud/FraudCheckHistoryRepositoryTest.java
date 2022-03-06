package com.spotifyclone.fraud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FraudCheckHistoryRepositoryTest {
    @Autowired
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Test
    public void returnNullWhenProfileIdNotExist() {
        FraudCheckHistory fraudCheckHistory = fraudCheckHistoryRepository.getFraudCheckHistoryByProfileId(444L);
        assertNull(fraudCheckHistory);
    }
}