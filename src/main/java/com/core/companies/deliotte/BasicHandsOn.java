package com.core.companies.deliotte;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasicHandsOn {
    public   static void main(String args []) {
        List<Integer> numbers = Arrays.asList(
                2, 5, 8, 12, 3, 7, 10, 15, 18, 20,
                25, 30, 5, 8, 12, 100, 105, -3, -10, 0
        );



        List<String> names = Arrays.asList(
                "Amit", "Rohit", "Ankit", "Neha", "Pooja",
                "Rahul", "Sneha", "Karan", "Priya", "Amit",
                "Ankit", "Zoya", "Akash", "Bob", "Alok", ""
        );




        List<String> words = Arrays.asList(
                "apple", "banana", "apple", "orange", "banana",
                "kiwi", "grape", "apple", "kiwi", "melon"
        );




        List<Integer> trickyNumbers = Arrays.asList(
                4, 5, 6, 4, 7, 5, 8, 9, 6, 10
        );

        // 🔷 Q1. First Non-Repeating Number
        Integer firstNonRepeating = trickyNumbers.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        System.out.println(firstNonRepeating); // 7



        List<Integer> partitionData = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        );









    }
}
