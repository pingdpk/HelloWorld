package com.company.example;

import com.company.example.interfc.AbstractTester;

public class AbstractMain {
    public static void main(String[] args) {
        AbstractTester tester = new AbstractTest();
        System.out.println(
                tester.findNumOfBedRooms() + "B"
                        + tester.findNumOfHalls() + "H"
                        + tester.findNumOfKitchens() + "K");
    }
}
