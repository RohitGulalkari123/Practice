package com.core.capgimini.streams.javafeatures.java11;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Java11 {
    public static void main(String[] args) {
        //var keyword in lambda
        Function<String, Integer> len = (var s) -> s.length();
        System.out.println(len.apply("Java"));

        //2. String APIs
        String str="   ";
        System.out.println(str.isBlank());

        String text="a\nb\nc";
        String[] arr=text.split("\n");
        System.out.println(Arrays.toString(arr));

        String star="*";
        System.out.println(star.repeat(5));





    }
}
