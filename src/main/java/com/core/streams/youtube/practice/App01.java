package com.core.streams.youtube.practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App01 {
    public static void main(String[] args) {

        //Given a sentence, find the word that has the highest length
        String str = "India Is My Country";
        String higestLenthWord = Arrays.stream(str.split(" ")).max(Comparator.comparing(String::length)).get();
        System.out.println("higestLenthWord :" + higestLenthWord);

        //02 Remove duplicates from the string and return in the same order
        String str2 = "abcdabcd";
        str2.chars().distinct().mapToObj(x -> (char) x).forEach(System.out::print);

        //03 Find the word that has the second highest length|
        String str3 = "India Is My Country";
        String skip = Arrays.stream(str3.split(" ")).sorted(Comparator.comparing(String::length).reversed()).skip(1).findFirst().get();
        System.out.println("skip :" + skip);

        //04 Find the 2nd highest length word in the given sentence|
        Integer secondHighestNoLenth = Arrays.stream(str3.split(" ")).map(String::length).sorted(Comparator.reverseOrder())
                .skip(1).findFirst().get();
        System.out.println("secondHighestNoLenth :" + secondHighestNoLenth);

        //05 Given a sentence, find the occurrence of each word
        String str4 = "India Is My Country Country India Is My";
        Map<String, Long> collect = Arrays.stream(str4.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("collect :" + collect);

        //06Given a sentence, find the words with a specified number of vowels  Words that are having 2 no of wowels
        List<String> stringStream = Arrays.stream(str4.split(" ")).filter(s -> s.replaceAll("[^aeiouAEIOU]", "").length() == 2).toList();
        System.out.println("stringStream :" + stringStream);

        //07 Divide given integer list into lists of even and odd numbers
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> integerlist = Arrays.stream(arr).boxed().toList();
        Map<Boolean, Long> evenoddMap = integerlist.stream().collect(Collectors.groupingBy(x -> x % 2 == 0, Collectors.counting()));
        System.out.println("Even Values :" + evenoddMap.get(true));
        System.out.println("Odd values :" + evenoddMap.get(false));

        List<Long> evenList = integerlist.stream()
                .collect(Collectors.groupingBy(x -> x % 2 == 0, Collectors.counting()))
                .entrySet()
                .stream()
                .map(x -> x.getValue()).toList();
        System.out.println("Even Values :" + evenList);

        //08 Given a word, find the occurrence of each character
        String s = "AllIsWell";
        Map<String, Long> collect1 = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("collect1 :" + collect1);

        //9 Arrange the numbers in Descending/Ascending Order
        int arr2[] = {4, 6, 8, 9, 3, 2, 3, 1, 5};
        Arrays.stream(arr2).mapToObj(x -> x).sorted().forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        Arrays.stream(arr2).mapToObj(x -> x).sorted(Collections.reverseOrder()).forEach(System.out::println);

        //10 Given an array, find the sum of unique elements
        int sum = Arrays.stream(arr2).distinct().sum();
        System.out.println("sum :" + sum);

        //11 Given a string, find the first non-repeated character
        String name = "Rohit";

        String result = Arrays.stream(name.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("result :" + result);

        String s1 = Arrays.stream(name.split("")).filter(c -> s.indexOf(c) == s.lastIndexOf(c)).findFirst().get();
        System.out.println(
                "First Character is " + s1
        );

        // 12 Given a string, find the first repeated character
        String name2 = "Roohit";


        String s2 = Arrays.stream(name2.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First Repected Character is " + s2);


        //13 Given an array of integers, group the numbers by the range
        String arr3[] = {"123", "456", "rohit", "toto"};
        List<Integer> list = Arrays.stream(arr3).filter(x -> x.matches("[0-9]]+")).map(Integer::valueOf).toList();
        System.out.println("list :" + list);

        //15 Find the products of the first two elements in an array.
        int arr5[] = {12, 5, 45, 5, 6, 7, 8};
        Integer reduceSumOftwoNos = Arrays.stream(arr5).boxed().toList().stream().limit(2).reduce(1, (a, b) -> a * b);
        System.out.println("Prodcut of firdst 2 element s :" + reduceSumOftwoNos);

    }


}
