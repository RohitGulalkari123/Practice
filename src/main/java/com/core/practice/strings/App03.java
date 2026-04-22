package com.core.practice.strings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App03 {
    public static void main(String[] args) {

        int longestSubstring = longestSubstring("abcabcbb");
        System.out.println(longestSubstring);
        System.out.println(secondLargest(new int[]{1, 5, 3, 9}));
        int arr[] = new int[]{1, 5, 3, 9};
        int n = 9;
        Set<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        List<Integer> list = IntStream.range(1, n).filter(i -> !collect.contains(i)).boxed().toList();
        System.out.println("list is " + list);
    }

    // 8 Longest substring
    static int longestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int l = 0, max = 0;
        for (int r = 0; r < s.length(); r++) {
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l++));
            }
            set.add(s.charAt(r));
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    // 11 Second largest
    static int secondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int n : arr) {
            if (n > first) {
                second = first;
                first = n;
            } else if (n > second && n != first) second = n;
        }
        return second;
    }


}