package com.transactionapi.TransactionAPI.api;

import com.transactionapi.TransactionAPI.entities.Transaction;
import com.transactionapi.TransactionAPI.services.ExchangeRateService;
import com.transactionapi.TransactionAPI.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/transactions")
@CrossOrigin
public class TransactionController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/exceeded/{id}")
    public List<Transaction> getExceededTransactions(@PathVariable(name = "id") Long id){
        return transactionService.getExceededTransactions(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction (@RequestBody Transaction transaction) throws IOException {
        String currency = transaction.getCurrency_shortname();
        double exchangeRate = 0;
        double sum = transaction.getSum();
        if(currency.equalsIgnoreCase("KZT")){
            exchangeRate = exchangeRateService.getExchangeRateKZT().getRate();
        }
        else if(currency.equalsIgnoreCase("RUB")){
            exchangeRate = exchangeRateService.getExchangeRateRub().getRate();
        }

        else if(currency.equalsIgnoreCase("USD")){
            exchangeRate = 1;
        }

        else{
            return null;
        }

        transaction.setSum(sum / exchangeRate);
        transactionService.addTransaction(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }



}
