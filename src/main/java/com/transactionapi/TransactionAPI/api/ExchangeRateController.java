package com.transactionapi.TransactionAPI.api;

import com.transactionapi.TransactionAPI.entities.ExchangeRateKzt;
import com.transactionapi.TransactionAPI.entities.ExchangeRateRub;
import com.transactionapi.TransactionAPI.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


//{"symbol":"USD/JPY","rate":135.185,"timestamp":1669960980}
//https://api.twelvedata.com/quote?symbol=USD/KZT&apikey=64d14e2d15374b1aa25506203a906377
/*
{"symbol":"USD/KZT","name":"US Dollar Kazakh Tenge","exchange":"Forex","datetime":"2022-12-02","timestamp":1669961728,"open":"467.85001","high":"469.35999","low":"467.35999","close":"469.35999","previous_close":"467.85001","change":"1.50998","percent_change":"0.32275","average_volume":"51942","is_market_open":true,"fifty_two_week":{"low":"411.85001","high":"527.12500","low_change":"57.50998","high_change":"-57.76501","low_change_percent":"13.96382","high_change_percent":"-10.95850","range":"411.850006 - 527.125000"}}
 */

@RestController
@RequestMapping("api/exchangerate")
@CrossOrigin
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;


    @GetMapping("/fromusdtokzt")
    public ExchangeRateKzt addExchangeRateKzt() throws IOException {
        return exchangeRateService.getExchangeRateKZT();
    }

    @GetMapping("/fromusdtorub")
    public ExchangeRateRub addExchangeRateRub() throws IOException {
        return exchangeRateService.getExchangeRateRub();
    }
}
