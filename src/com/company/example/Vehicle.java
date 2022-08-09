package com.company.example;

public class Vehicle {
    private Car car;

    public Car getCar() {
        if(car == null){
            System.out.println("initialized");
            Car tempCar = new Car();
            this.car = tempCar;
            return tempCar;
        }
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
