package com.ender.staticproxy;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Agent implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("打开资源");
        System.out.println(method.getName());
        if(args != null){
            System.out.println(args[0].getClass().getSimpleName());
        }
        System.out.println("关闭资源");
        return null;
    }

    public static <T>T getProxy(Class<T> target){
        Agent agent = new Agent();
        Object o = Proxy.newProxyInstance(Agent.class.getClassLoader(), new Class[]{}, agent);
        return (T)o;
    }
}
