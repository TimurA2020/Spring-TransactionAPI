package com.transactionapi.TransactionAPI.api;

import com.transactionapi.TransactionAPI.entities.Account;
import com.transactionapi.TransactionAPI.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public List<Account> getAllAccountsInfo(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/add")
    public ResponseEntity<Account> setAccountLimits (@RequestBody Account account) {
        return new ResponseEntity<>((accountService.setLimits(account.getId(), account.getService_limit(), account.getProduct_limit())),
                HttpStatus.CREATED);
    }


    }
