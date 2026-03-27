package com.core.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App01 {
    public static void main(String[] args) {

        //Find frequency of elements using Streams
        List<String> list = Arrays.asList("a", "b", "a", "c", "b", "a");
        Map<String, Long> freqMap = list.stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(freqMap);

    }
}
