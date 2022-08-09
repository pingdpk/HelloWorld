package com.company.example.impl;

import com.company.example.BankAccount;
import com.company.example.interfc.Account;

import java.math.BigDecimal;

public class BankDirect implements Account {

    //BankAccount bankAccount = new BankAccount();

    @Override
    public BigDecimal getBalance() {
        return BankAccount.balance;
    }

    @Override
    public BigDecimal addAmount(BigDecimal amount) {
        return null;
    }

    @Override
    public BigDecimal withDrawAmount(BigDecimal amount) {
        return null;
    }

    @Override
    public void showNotification() {
        System.out.println("Notification from main account through direct bank");
    }
}
