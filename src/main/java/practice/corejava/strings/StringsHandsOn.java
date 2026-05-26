package practice.corejava.strings;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringsHandsOn {
    public static void main(String[] args) {
        //First Non-Repeated Character
        String str = "aaBdcf";
        Character firstNonRepeatingChar = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(v -> v.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("firstNonRepeatingChar :" + firstNonRepeatingChar);


        // - [x] [ ] Character Frequency Count
        String str2 = "abbcccddddeeeeefffffff";
        LinkedHashMap<Character, Long> charsCount = str2.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("charsCount :" + charsCount);

        //- [x] [ ] Remove Duplicate Characters
        Set<Character> unique = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char arr[] = str2.toCharArray();
        for (char c : arr) {
            unique.add(c);
        }
        for (char c : unique) {
            sb.append(c);
        }
        System.out.println("After removing duplicates :" + sb.toString());
        //- [x] [ ] Anagram Check
        String s1 = "silent";
        String s2 = "listen";
        boolean b = checkIsAnagram(s1, s2);
        System.out.println("Is Anagram ? " + b);

        //- [x] [ ] Palindrome String
        boolean madam = checkIfPalindrome("madam");
        System.out.println("Is Palindrome ? " + madam);

        //STring compression
        String compressedString = compressString("abbcccddddfffff");
        System.out.println("compressedString :" + compressedString);

        calculateVowals("aeiouabcdf");




    }

    private static void calculateVowals(String str) {
        int vowals = 0;
        int cons = 0;

        for (char c : str.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) != -1) {
                vowals++;
            } else {
                cons++;
            }
       }

        System.out.println("vowals :" + vowals);
        System.out.println("cons :" + cons);
    }

    private static String compressString(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                sb.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        return sb.toString();
    }

    private static boolean checkIfPalindrome(String str) {
        char arr[] = str.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (!(arr[left] == arr[right])) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIsAnagram(String a, String b) {
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);

    }
}
