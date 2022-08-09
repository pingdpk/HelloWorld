package com.company.example;

import com.company.example.impl.BankATM;
import com.company.example.impl.BankDirect;
import com.company.example.interfc.Account;
import com.company.example.interfc.MainAccount;

import java.math.BigDecimal;

public class MainBank {
    public static void main(String[] args) {
        BankDirect bankDirect = new BankDirect();
        System.out.println("curr balance (cheq): " + bankDirect.getBalance());

        BankATM bankATM = new BankATM();

        System.out.println("Curr balance: " + bankATM.getBalance());

        bankATM.addAmount(new BigDecimal("100.001"));
        System.out.println("Curr balance: " + bankATM.getBalance());

        bankATM.withDrawAmount(new BigDecimal("50.123"));
        System.out.println("Curr balance: " + bankATM.getBalance());


        //BankCheck bankCheck = new BankCheck();
        System.out.println("curr balance (direct): " + bankDirect.getBalance());

        bankATM.showNotification();
        bankDirect.showNotification();

        Account account_of_ATM = new BankATM();
        Account account_of_direct = new BankDirect();

        System.out.println(account_of_ATM.getBalance());
        System.out.println(account_of_direct.getBalance());
        account_of_ATM.showNotification();
        account_of_direct.showNotification();

        MainAccount mainAccount_of_ATM = new BankATM();
        MainAccount mainAccount_of_direct = new BankDirect();

        mainAccount_of_ATM.showNotification();
        mainAccount_of_direct.showNotification();






        Vehicle vehicle = new Vehicle();
        vehicle.getCar().setWheelCount(1);


    }




}
