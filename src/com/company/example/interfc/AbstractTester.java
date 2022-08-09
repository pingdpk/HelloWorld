package com.company.example.interfc;

import com.company.example.Car;

public interface AbstractTester {
    int findNumOfBedRooms();
    int findNumOfHalls();
    default int findNumOfKitchens(){
        Car car = new Car();
        car.setWheelCount(1);
        return car.getWheelCount();
    }
}
