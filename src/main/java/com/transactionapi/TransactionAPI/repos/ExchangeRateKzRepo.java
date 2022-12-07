package com.transactionapi.TransactionAPI.repos;

import com.transactionapi.TransactionAPI.entities.ExchangeRateKzt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ExchangeRateKzRepo extends JpaRepository<ExchangeRateKzt, Long> {
    ExchangeRateKzt findTopByOrderByIdDesc();
}
