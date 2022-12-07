package com.transactionapi.TransactionAPI.services;

import com.transactionapi.TransactionAPI.entities.Account;
import com.transactionapi.TransactionAPI.entities.Transaction;
//import com.transactionapi.TransactionAPI.repos.TransactionRepo;
import com.transactionapi.TransactionAPI.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountService accountService;

    public List<Transaction> getAllTransactions(){
        return transactionRepo.findAll();
    }

    public Transaction addTransaction(Transaction transaction){
        double limit = 0;
        Long sender_id = transaction.getAccount_from();
        String category = transaction.getExpense_category();
        Account sender = accountService.getAccount(sender_id);
        if(sender == null){
            System.out.println("accountnotfounderror=true");
            return null;
        }
        if(category.equalsIgnoreCase("product")){
            limit = sender.getProduct_limit() - transaction.getSum();
            sender.setProduct_limit(limit);
        }
        else if(category.equalsIgnoreCase("service")){
            limit = sender.getService_limit() - transaction.getSum();
            sender.setService_limit(limit);
        }
        else{
            System.out.println("transactioncategoryerror=true");
            return null;
        }

        transaction.setDatetime(new Timestamp(System.currentTimeMillis()));

        accountService.updateAccount(sender);
        transaction.setLimit_exceeded(limit < 0);

        return transactionRepo.save(transaction);
    }

    public List<Transaction> getExceededTransactions(Long id){
        List<Transaction> transactions = transactionRepo.findAllBySender(id);
        List<Transaction> exceededTransactions = new ArrayList<>();

        try {

            for (Transaction t : transactions) {
                if (t.isLimit_exceeded()) {
                    exceededTransactions.add(t);
                }
            }

            return exceededTransactions;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

}
