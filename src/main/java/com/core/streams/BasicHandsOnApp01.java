package com.core.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicHandsOnApp01 {
    public static void main(String[] args) {

        //01 Even / Odd
        int n = 8;
        IntStream.of(n).mapToObj(x -> x + " is " + ((x % 2 == 0) ? "Even" : "Odd")).forEach(System.out::println);

        //Filter even numbers from a list
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNoList = numbers.stream().filter(x -> x % 2 == 0).toList();
        System.out.println("evenNoList:" + evenNoList);

        //03 Fibonacci Series
        fibonacci(5);

        // Stream.iterate — infinite stream, limit to N
        Stream.iterate(new long[]{0, 1}, f -> new long[]{
                        f[1], f[0] + f[1]}).limit(10).map(f -> f[0])
                .forEach(x -> System.out.print(x + " "));


        //Output: 0 1 1 2 3 5 8 13 21 34

    }

    static void fibonacci(int n) {
        //first no and second no  equal to tghe next no
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            System.out.println(a + " ");
            int nextNo = a + b;
            a = b;
            b = nextNo;
        }
    }
}
