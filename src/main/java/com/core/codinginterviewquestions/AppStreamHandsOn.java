package com.core.codinginterviewquestions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppStreamHandsOn {
    public static void main(String[] args) {
        //* Find highest length word in sentence
        String str = "Hi there i am using watsapp";
        String highestLengthWord =
                Arrays.stream(str.split("\\s+")).max(Comparator.comparingInt(String::length
                )).orElse(null);
        System.out.println("highestLengthWord :" + highestLengthWord);

        //2. Find Second Highest Length Word from the sentance
        String secondHighest = Arrays.stream(str.split("\\s+")).sorted(
                Comparator.comparingInt(String::length).reversed()
        ).skip(1).toList().get(0);
        System.out.println("secondHighest :" + secondHighest);


        //2. Find Second Highest Length Word from the sentance
        List<String> list =
                Arrays.asList(
                        "Java",
                        "SpringBoot",
                        "Kafka",
                        "Microservices",
                        "Redis"
                );

        Optional<String> first = list.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1).findFirst();
        System.out.println(first.get());

        //3. Find Second Highest Length Word in Sentence
        String str1 = " ab abc abcd";
        Optional<String> first1 = Arrays.stream(str1.split("\\s+")).sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1).findFirst();
        System.out.println(first1.get());


        //4. Find Occurrence of Each Word
        Map<String,Long> map=
                Arrays.stream(
                                str1.split("\\s+")).filter(s -> !s.isEmpty())
                        .collect(
                                Collectors.groupingBy(
                                        Function.identity(),
                                        Collectors.counting()
                                ));

        System.out.println(map);

        //5. Find Words with Specified Number of Vowels



    }
}
