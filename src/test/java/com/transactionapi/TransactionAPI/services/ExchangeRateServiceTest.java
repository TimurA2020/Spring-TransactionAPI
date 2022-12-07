package com.transactionapi.TransactionAPI.services;

import com.transactionapi.TransactionAPI.entities.ExchangeRateKzt;
import com.transactionapi.TransactionAPI.entities.ExchangeRateRub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeRateServiceTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Test
    void getExchangeRateKZT() throws IOException {
        ExchangeRateKzt rate = exchangeRateService.getExchangeRateKZT();
        Assertions.assertNotNull(rate);
    }

    @Test
    void getExchangeRateRub() throws IOException {
        ExchangeRateRub rate = exchangeRateService.getExchangeRateRub();
        Assertions.assertNotNull(rate);
    }

}