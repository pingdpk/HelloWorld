package com.company.example.interfc;

import java.math.BigDecimal;

public interface Account extends MainAccount{
    //public BigDecimal balance = new BigDecimal("0.000");
    public BigDecimal getBalance();
    public BigDecimal addAmount(BigDecimal amount);
    public BigDecimal withDrawAmount(BigDecimal amount);
}