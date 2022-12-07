package com.transactionapi.TransactionAPI.services;

import com.transactionapi.TransactionAPI.entities.Account;
import com.transactionapi.TransactionAPI.entities.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Test
    void addTransaction() {
        Account sender = accountService.addAccount(550, 300);
        Long senderId = sender.getId();
        Account receiver = accountService.addAccount(300, 550);
        Long receiverId = receiver.getId();

        Transaction transaction = new Transaction();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaction.setAccount_to(receiverId);
        transaction.setAccount_from(senderId);
        transaction.setExpense_category("Product");
        transaction.setCurrency_shortname("USD");
        transaction.setLimit_exceeded(false);
        transaction.setSum(100);

        Transaction addedTransactions = transactionService.addTransaction(transaction);

        Assertions.assertNotNull(addedTransactions);
        Assertions.assertEquals(addedTransactions.getAccount_from(), senderId);
        Assertions.assertEquals(addedTransactions.getAccount_to(), receiverId);
        Assertions.assertEquals(addedTransactions.getExpense_category(), "Product");
        Assertions.assertEquals(addedTransactions.getCurrency_shortname(), "USD");
        Assertions.assertEquals(addedTransactions.isLimit_exceeded(), false);


        //Testing if the product limit was decreased by 100
        Account updatedSenderAccount = accountService.getAccount(senderId);
        Assertions.assertEquals(updatedSenderAccount.getProduct_limit(), sender.getProduct_limit() - 100);

    }

    @Test
    void getExceededTransactions(){
        Account sender = accountService.addAccount(550, 300);
        Long senderId = sender.getId();
        Account receiver = accountService.addAccount(300, 550);
        Long receiverId = receiver.getId();

        Transaction transaction = new Transaction();
        transaction.setAccount_to(receiverId);
        transaction.setAccount_from(senderId);
        transaction.setExpense_category("Product");
        transaction.setCurrency_shortname("USD");
        transaction.setSum(100);

        Transaction addedTransaction = transactionService.addTransaction(transaction);

        Transaction transaction2 = new Transaction();
        transaction2.setAccount_to(receiverId);
        transaction2.setAccount_from(senderId);
        transaction2.setExpense_category("Product");
        transaction2.setCurrency_shortname("USD");
        transaction2.setSum(400);

        Transaction addedTransaction2 = transactionService.addTransaction(transaction2);

        Transaction transaction3 = new Transaction();
        transaction3.setAccount_to(receiverId);
        transaction3.setAccount_from(senderId);
        transaction3.setExpense_category("Product");
        transaction3.setCurrency_shortname("USD");
        transaction3.setSum(200);

        Transaction addedTransaction3 = transactionService.addTransaction(transaction3);


        Transaction transaction4 = new Transaction();
        transaction4.setAccount_to(receiverId);
        transaction4.setAccount_from(senderId);
        transaction4.setExpense_category("Product");
        transaction4.setCurrency_shortname("USD");
        transaction4.setSum(300);

        Transaction addedTransaction4 = transactionService.addTransaction(transaction4);

        List<Transaction> exceededTransactions = transactionService.getExceededTransactions(senderId);

        //Since two transactions exceed the limit, the list.size should be equal to 2
        Assertions.assertEquals(exceededTransactions.size(), 2);
        Assertions.assertEquals(exceededTransactions.get(0).getId(), addedTransaction3.getId());
        Assertions.assertEquals(exceededTransactions.get(1).getId(), addedTransaction4.getId());


    }
}