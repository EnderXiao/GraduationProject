package com.ender;

/**
 * @author Ender-PC
 */
public class BMWFactory implements Factory{
    @Override
    public Car getCar() {
        return new BMW();
    }
}
