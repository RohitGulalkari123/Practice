package com.core.practice.strings;

import java.util.*;

public class App02 {
    public static void main(String[] args) {
        // Reverse
        System.out.println(reverse("rohit"));
        // Palindrome
        System.out.println("isPalindrome ? :" + isPalindrome("madam"));
        // First Non-Repeating
        System.out.println(firstNonRepeat("aabbc"));
        // First Repeating
        System.out.println(firstRepeat("abca"));

        frequency("aabbcc");

        System.out.println("isAnagram ? :" + isAnagram("listen", "silent"));

// Remove duplicates
        System.out.println(removeDuplicates("aabbcc"));
    }

    static String reverse(String str) {
        char[] arr = str.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        char temp;
        while (left < right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }

    static boolean isPalindrome(String str) {
        char[] arr = str.toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 3 First Non Repeat
    static char firstNonRepeat(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) == 1) return c;
        }
        return '_';
    }

    // 3 First Non Repeat
    static char firstRepeat(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) return c;  // will return if the value is there is in set : set only store unique values
        }
        return '_';
    }

    // 5 Frequency
    static void frequency(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        System.out.println(map);
    }

    static boolean isAnagram(String s1, String s2) {
        System.out.println("S1 and S2 : " + s1 + "  " + s2);
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    static String removeDuplicates(String s) {
        Set<Character> set = new LinkedHashSet<>();
        for (char c : s.toCharArray()) set.add(c);
        StringBuilder sb = new StringBuilder();
        for (char c : set) sb.append(c);
        return sb.toString();
    }
}
