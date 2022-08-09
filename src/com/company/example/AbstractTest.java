package com.company.example;

import com.company.example.interfc.AbstractTester;

public class AbstractTest implements AbstractTester {
    @Override
    public int findNumOfBedRooms(){
        return 2;
    }

    @Override
    public int findNumOfHalls(){
        return 1;
    }
}
