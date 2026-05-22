package com.core.capgimini.streams.javafeatures.designattern.singletone;

import java.lang.reflect.InvocationTargetException;

public class TestSingletone {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MongoConnection instance1 = MongoConnection.getInstance();
        //MongoConnection instance2 = MongoConnection.getInstance();
        //System.out.println(instance1 == instance2);

        /**
         * How Singletone can be breaked down
         *
         * */
        //1. Reflection Attack

        /*Constructor<MongoConnection> c =
                MongoConnection.class
                        .getDeclaredConstructor();
        c.setAccessible(true);
        MongoConnection m2 =
                c.newInstance();
        System.out.println(m2 == instance1);*/

        //2. Serialization Attack




    }
}
