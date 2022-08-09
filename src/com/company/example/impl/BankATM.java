package com.company.example.impl;

import com.company.example.BankAccount;
import com.company.example.interfc.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankATM implements Account {
    //BigDecimal balance = new BigDecimal("0.000").setScale(2, RoundingMode.HALF_DOWN);

    //BankAccount bankAccount = new BankAccount();

    @Override
    public BigDecimal getBalance() {
        return BankAccount.balance;
    }

    @Override
    public BigDecimal addAmount(BigDecimal amount) {
        return BankAccount.setBalance(BankAccount.balance.add(amount));
    }

    @Override
    public BigDecimal withDrawAmount(BigDecimal amount) {
        return BankAccount.setBalance(BankAccount.balance.subtract(amount));
    }

    @Override
    public void showNotification() {
        System.out.println("Notification from main account through ATM");
    }
}