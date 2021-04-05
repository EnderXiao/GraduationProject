package com.ender.staticproxy;

import jdk.jfr.DataAmount;

public class MaleSinger implements Singer{
    private String name;

    public MaleSinger(String name) {
        this.name = name;
    }

    @Override
    public void sing(){
        System.out.println(this.name+"开始唱歌了");
    }

    @Override
    public void play() {

    }

    @Override
    public void act() {

    }

    @Override
    public void eat() {

    }
}
