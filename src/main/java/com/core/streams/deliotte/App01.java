package com.core.streams.deliotte;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App01 {
    public static void main(String[] args) {

        //7. Given Sentence Find Highest Length Word
        String str = "I love Java programming language";

        String result = Arrays.stream(str.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        System.out.println(result);

        //8. Second Highest Length Word
        String secondHighjestLenthWord = Arrays.stream(str.split(" "))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1).findFirst().orElse("");

        System.out.println("secondHighjestLenthWord :" + secondHighjestLenthWord);

        //9. Remove Duplicates From String Maintain Order
        String rd = "abcdancd";
        String collect = rd.chars().mapToObj(c -> (char) c).distinct().map(String::valueOf).collect(Collectors.joining());
        System.out.println("collect :" + collect);

        // 10. Occurrence of Each Word
        String occ = "java is good java is powerful";
        Map<String, Long> collect1 = Arrays.stream(occ.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("collect1 :" + collect1);

        //11. Occurrence of Each Character
        String occ1 = "java is good java is powerful";
        Map<Character, Long> characterCout = occ1.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("characterCout :" + characterCout);

        //12. First Non-Repeated Character
        Character fnRc = str.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("fnRc :" + fnRc);

        //Fisrst repated character
        Character reaptedChar = str.chars()
                .mapToObj(c -> (char)c)
                .filter(c -> str.indexOf(c) != str.lastIndexOf(c))
                .findFirst()
                .orElse(null);








    }
}
