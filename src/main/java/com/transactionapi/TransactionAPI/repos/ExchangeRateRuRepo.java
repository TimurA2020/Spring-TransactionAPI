package com.transactionapi.TransactionAPI.repos;

import com.transactionapi.TransactionAPI.entities.ExchangeRateKzt;
import com.transactionapi.TransactionAPI.entities.ExchangeRateRub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ExchangeRateRuRepo extends JpaRepository<ExchangeRateRub, Long> {
    ExchangeRateRub findTopByOrderByIdDesc();

}
