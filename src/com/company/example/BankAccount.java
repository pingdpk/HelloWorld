package com.company.example;

import java.math.BigDecimal;

public class BankAccount {
    public static BigDecimal balance = new BigDecimal("0.00");

    public static BigDecimal setBalance(BigDecimal newBalance) {
        balance = newBalance;
        return balance;
    }

}
