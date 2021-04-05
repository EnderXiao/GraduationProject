package com.ender;

/**
 * @author Ender
 */
public class BenzFactory implements Factory{

    @Override
    public Car getCar() {
        return new Benz();
    }
}
