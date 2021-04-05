package com.ender;

public class BikeFactory implements Factory{
    @Override
    public Car getCar() {
        return new Bike();
    }
}
