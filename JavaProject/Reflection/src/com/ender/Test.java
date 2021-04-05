package com.ender;

/**
 * @author Ender-PC
 */
public class Test {
    public static void main(String[] args) {
        BMWFactory bmwFactory = new BMWFactory();
        Car bmw = bmwFactory.getCar();
        bmw.run();
        BenzFactory benzFactory = new BenzFactory();
        Car benz = benzFactory.getCar();
        benz.run();
    }
}
