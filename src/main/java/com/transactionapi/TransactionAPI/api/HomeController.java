package com.transactionapi.TransactionAPI.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String getIndex(){
        return "index";
    }

    @GetMapping(value = "/sendtransaction")
    public String sendTransaction(){
        return "sendtransaction";
    }

    @GetMapping(value = "/setlimit")
    public String getLimitPage(){
        return "setlimit";
    }

    @GetMapping(value = "/listaccounts")
    public String listAccounts(){
        return "listaccounts";
    }

    @GetMapping(value = "/listtransactions")
    public String listTransactions(){
        return "listtransactions";
    }
}
