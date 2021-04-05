package com.ender;

/**
 * @author Ender
 */
public class CarFactory {
    /**
     * 根据对象名获取实列对象的方法
     * @param name
     * @return
     */
    public Car getCar(String name){
        if("bmw".equalsIgnoreCase(name)){
            //其中可能有很复杂的操作
            return new BMW();
        } else if("benz".equalsIgnoreCase(name)){
            //其中可能有很复杂的操作
            return new Benz();
        }
        return new Bike();
    }
}
