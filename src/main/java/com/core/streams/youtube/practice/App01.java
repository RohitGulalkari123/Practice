package com.core.streams.youtube.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

        //06Given a sentence, find the words with a specified number of vowels
        List<String> stringStream = Arrays.stream(str4.split(" ")).filter(s -> s.replaceAll("^aeiouAEIOU", "").length() == 2).toList();
        System.out.println("stringStream :" + stringStream);


    }
}
