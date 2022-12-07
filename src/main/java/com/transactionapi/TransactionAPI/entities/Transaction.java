package com.transactionapi.TransactionAPI.entities;

//https://api.twelvedata.com/exchange_rate?symbol=USD/KZT&apikey=64d14e2d15374b1aa25506203a906377

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long account_from;

    private Long account_to;

    private String currency_shortname;

    private double sum;

    private String expense_category;

    private Timestamp datetime;

    private boolean limit_exceeded;

}
