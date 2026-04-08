package com.core.multithreding.designpatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletoneDesignPatternDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Config  config = Config.getInstance();
        Config  config2 = Config.getInstance();

        System.out.println(config.hashCode());
        System.out.println(config2.hashCode());

        Constructor<Config> constructor =
                Config.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Config s1 = constructor.newInstance();
        Config s2 = constructor.newInstance();

        //Will have same hashcode here using reflection
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
    }
}
