package com.transactionapi.TransactionAPI.repos;

import com.transactionapi.TransactionAPI.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account, Long> {
}
