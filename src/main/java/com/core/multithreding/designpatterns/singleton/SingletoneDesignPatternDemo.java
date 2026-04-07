package com.core.multithreding.designpatterns.singleton;

public class SingletoneDesignPatternDemo {
    public static void main(String[] args) {
        Config  config = Config.getInstance();
        Config  config2 = Config.getInstance();

        System.out.println(config.hashCode());
        System.out.println(config2.hashCode());
    }
}
