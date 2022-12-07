package com.transactionapi.TransactionAPI.services;

import com.transactionapi.TransactionAPI.entities.Account;
import com.transactionapi.TransactionAPI.repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public List<Account> getAllAccounts(){
        List<Account> accounts = accountRepo.findAll();
        accounts.sort(Comparator.comparing(Account::getId));
        return accounts;
    }

    public Account addAccount(double product_limit, double service_limit){
        Account account = new Account();
        account.setProduct_limit(product_limit);
        account.setService_limit(service_limit);
        return accountRepo.save(account);
    }

    public Account getAccount(Long id){
        return accountRepo.findById(id).orElseThrow();
    }

    public void updateAccount(Account account){
        accountRepo.save(account);
    }

    public Account setLimits(Long id, double service_limit, double product_limit){
        Account account = accountRepo.findById(id).orElseThrow();
        if(account != null){
            account.setService_limit(service_limit);
            account.setProduct_limit(product_limit);
            return accountRepo.save(account);
        }else{
            System.out.println("Account could not be found");
            return null;
        }
    }
}
