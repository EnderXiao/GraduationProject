package com.ender;

/**
 * @author Ender
 */
public class Bike implements Car{
    @Override
    public void run() {
        System.out.println("没办法只能自行车了");
    }
}
