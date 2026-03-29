package com.core.streams.app01;

import java.util.*;
import java.util.stream.Collectors;

public class BasicQue {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 2, 4, 1, 5, 5, 5, 5, 5, 5);
        String str = "aabbcde";

        //  1. Find Frequency :: how many times 1 came? how many times 2 came?
        Map<Integer, Long> frqMap = list.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println("Frequency map is :" + frqMap);

        //2. Find Duplicates :: Who came more than once
        Set<Integer> unique = new HashSet<>();
        Set<Integer> uniqueSet = list.stream().filter(x -> !unique.add(x)).collect(Collectors.toSet());
        System.out.println("uniqueSet is : " + uniqueSet);

        // 3. First Non-Repeated Character :: Find first kid who came only once
        //Use map here like LinkedHashMap and then count the entry
        Character firstNonRepChar = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("firstNonRepChar is : " + firstNonRepChar);



    }
}
