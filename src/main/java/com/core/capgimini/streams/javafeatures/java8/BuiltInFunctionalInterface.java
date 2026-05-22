package com.core.capgimini.streams.javafeatures.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuiltInFunctionalInterface {
    public static void main(String[] args) {
        Predicate<Integer> predicate = i -> i % 2 == 0;
        System.out.println("Is Even ? :" + predicate.test(7));

        //Will take String and give Integer  -> Mapping COntext
        Function<String, Integer> function = String::length;
        System.out.println("Length of String  ? :" + function.apply("Even"));

        //Only Consume not did anything
        Consumer<String> consumer = System.out::println;
        consumer.accept("Consumer Consuming the Input ");

        //Will Supply something like UUID,Uetr,RandonIdGenerator etc
        Supplier<Double> s =
                Math::random;
        System.out.println("Random Number  ? :" + s.get());


    }
}
