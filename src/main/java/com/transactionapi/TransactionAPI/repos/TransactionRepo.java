package com.transactionapi.TransactionAPI.repos;

import com.transactionapi.TransactionAPI.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("Select t FROM Transaction t WHERE t.account_from = ?1")
    List<Transaction> findAllBySender(Long id);

}
