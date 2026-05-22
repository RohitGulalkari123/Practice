package com.core.capgimini.streams.javafeatures.java8;

@FunctionalInterface
public interface FunctionalInterfaceDemo {
    int add(int a,int b);

    default void show(){
        System.out.println("Calling Default Method from Functional Interface ");
        helper();//deafult using in deafult method internally in dunctional interface
    }

    static void display(){
        System.out.println("Calling Display Method from Functional Interface Static : ");
    }

    private void helper(){
        System.out.println("Private");
    }
}
