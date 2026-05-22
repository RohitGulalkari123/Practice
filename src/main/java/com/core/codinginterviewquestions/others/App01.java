package com.core.codinginterviewquestions.others;

import java.util.*;

public class App01 {
    public static void main(String[] args) {
        // Reverse String
        System.out.println(reverseString("hello"));

        // Palindrome
        System.out.println(isPalindromeOrNot("madam"));

        // First Non-Repeating
        System.out.println(firstNonRepeatCharacter("rrohit"));

        // First Repeating
        System.out.println(firstRepeatCharacter("abcdd"));

        // Frequency Count
        frequencyCount("aabbcc");

        // Anagram
        System.out.println(isStringAnagram("listen", "silent"));

        //Remove duplicates
        System.out.println(removeDuplicatesFromString("aabbcc"));

        // Digit check
        System.out.println(isTheseDigits("12345"));

        // Capitalize
        System.out.println(capatalizedFirstWord("hello world"));

        // Second largest
        System.out.println(secondLargestNo(new int[]{1, 5, 3, 9}));

        //Missing number
        System.out.println(findMissingNoInArray(new int[]{1, 2, 4, 5}, 5));

        // Duplicate
        System.out.println(findDuplicateinArray(new int[]{1, 2, 3, 5, 2}));

// Pair sum
        findPairSum(new int[]{1, 2, 3, 4}, 5);

        // Move zero
        // System.out.println(Arrays.toString(moveZero(new int[]{0,1,0,3})));


    }



    private static void findPairSum(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        for (int n : arr) {
            if (set.contains(target - n))
                System.out.println(n + " " + (target - n));
            set.add(n);
        }

    }

    private static int findDuplicateinArray(int[] ints) {
        Set<Integer> set = new HashSet<>();
        for (int i : ints) {
            if (!set.add(i)) {
                return i;
            }
        }
        return -1;
    }

    private static int findMissingNoInArray(int[] ints, int n) {
        int sum = n * (n + 1) / 2;
        for (int i : ints) {
            sum -= i;
        }
        return sum;
    }


    private static int secondLargestNo(int[] ints) {
        int first = Integer.MIN_VALUE, second = Integer.MAX_VALUE;
        for (int n : ints) {
            if (n > first) {
                second = first;
                first = n;
            } else if (n > second && n != first) {
                second = n;
            }
        }
        return second;
    }

    static String capatalizedFirstWord(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    private static boolean isTheseDigits(String number) {
        return number.matches("\\d+");

    }

    private static String removeDuplicatesFromString(String aabbcc) {
        Set<Character> set = new LinkedHashSet<>();
        for (char c : aabbcc.toCharArray()) {
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : set) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static boolean isStringAnagram(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    private static void frequencyCount(String str) {
        char charArr[] = str.toCharArray();
        Map<Character, Integer> freqCharMap = new LinkedHashMap<>();
        for (char c : charArr) {
            freqCharMap.put(c, freqCharMap.getOrDefault(c, 0) + 1);
        }
        System.out.println("freqCharMap : " + freqCharMap);
    }

    private static char firstRepeatCharacter(String abca) {
        char[] chArr = abca.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : chArr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) > 1) {
                return c;
            }
        }
        return '-';
    }

    private static char firstNonRepeatCharacter(String aabbc) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : aabbc.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return '-';
    }

    private static boolean isPalindromeOrNot(String str) {
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

    private static String reverseString(String str) {
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
}
