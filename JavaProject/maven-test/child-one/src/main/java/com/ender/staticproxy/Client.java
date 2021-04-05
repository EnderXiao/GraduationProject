package com.ender.staticproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        Singer proxy = Agent.getProxy(Singer.class);
        proxy.sing();


        IUserDao proxy1 = Agent.getProxy(IUserDao.class);
        proxy1.saveUser("Ender");

    }
}
