package com.transactionapi.TransactionAPI.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "exchangeru")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rate;

    private Timestamp date;
}
