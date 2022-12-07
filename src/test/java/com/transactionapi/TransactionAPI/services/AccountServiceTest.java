package com.transactionapi.TransactionAPI.services;

import com.transactionapi.TransactionAPI.entities.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void addAccount(){
        Account account = accountService.addAccount(550, 300);
        Assertions.assertNotNull(accountService.getAccount(account.getId()));
    }

    @Test
    void getAccount() {
        Account account = accountService.addAccount(550, 300);
        Assertions.assertEquals(accountService.getAccount(account.getId()).getProduct_limit(), 550);
        Assertions.assertEquals(accountService.getAccount(account.getId()).getService_limit(), 300);
    }


    @Test
    void setLimits() {
        Account account = accountService.addAccount(550, 300);
        Long id = account.getId();
        accountService.setLimits(id, 100, 200);
        Account updatedAccount = accountService.getAccount(id);
        Assertions.assertEquals(updatedAccount.getService_limit(), 100);
        Assertions.assertEquals(updatedAccount.getProduct_limit(), 200);
    }
}