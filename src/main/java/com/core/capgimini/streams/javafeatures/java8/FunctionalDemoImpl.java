package com.core.capgimini.streams.javafeatures.java8;

public class FunctionalDemoImpl {
    public static void main(String[] args) {
        FunctionalInterfaceDemo fun=(a, b)-> a+b;
        int add = fun.add(4, 5);
        System.out.println("Addition of given no is :"+add);

        //Static Method call
        fun.show();

        //Dafault Method call
        FunctionalInterfaceDemo.display();

    }
}
