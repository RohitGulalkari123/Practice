package com.core.practice.strings;

import java.util.*;

public class DSA_POC {

    public static void main(String[] args) {

        // Reverse String
        System.out.println(reverse("hello"));

        // Palindrome
        System.out.println(isPalindrome("madam"));

        // First Non-Repeating
        System.out.println(firstNonRepeat("aabbc"));

        // First Repeating
        System.out.println(firstRepeat("abca"));

        // Frequency Count
        frequency("aabbcc");

        // Anagram
        System.out.println(isAnagram("listen", "silent"));

        // Remove duplicates
        System.out.println(removeDuplicates("aabbcc"));

        // Longest substring
        System.out.println(longestSubstring("abcabcbb"));

        // Digit check
        System.out.println(isDigits("12345"));

        // Capitalize
        System.out.println(capitalize("hello world"));

        // Second largest
        System.out.println(secondLargest(new int[]{1,5,3,9}));

        // Missing number
        System.out.println(missingNumber(new int[]{1,2,4,5},5));

        // Duplicate
        System.out.println(findDuplicate(new int[]{1,2,3,2}));

        // Pair sum
        pairSum(new int[]{1,2,3,4},5);

        // Move zeros
        System.out.println(Arrays.toString(moveZero(new int[]{0,1,0,3})));

        // Min max
        minMax(new int[]{2,5,1,9});

        // Intersection
        System.out.println(intersection(new int[]{1,2,3}, new int[]{2,3,4}));

        // Kth largest
        System.out.println(kthLargest(new int[]{3,2,1,5,6},2));
    }

    // 1 Reverse
    static String reverse(String s){
        char[] arr = s.toCharArray();
        int l=0, r=arr.length-1;
        while(l<r){
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
        return new String(arr);
    }

    // 2 Palindrome
    static boolean isPalindrome(String s){
        int l=0,r=s.length()-1;
        while(l<r){
            if(s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    // 3 First Non Repeat
    static char firstNonRepeat(String s){
        Map<Character,Integer> map = new LinkedHashMap<>();
        for(char c: s.toCharArray()) map.put(c,map.getOrDefault(c,0)+1);
        for(char c: map.keySet()) if(map.get(c)==1) return c;
        return '_';
    }

    // 4 First Repeat
    static char firstRepeat(String s){
        Set<Character> set = new HashSet<>();
        for(char c: s.toCharArray()){
            if(!set.add(c)) return c;
        }
        return '_';
    }

    // 5 Frequency
    static void frequency(String s){
        Map<Character,Integer> map = new HashMap<>();
        for(char c: s.toCharArray()) map.put(c,map.getOrDefault(c,0)+1);
        System.out.println(map);
    }

    // 6 Anagram
    static boolean isAnagram(String a, String b){
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1,c2);
    }

    // 7 Remove duplicates
    static String removeDuplicates(String s){
        Set<Character> set = new LinkedHashSet<>();
        for(char c: s.toCharArray()) set.add(c);
        StringBuilder sb = new StringBuilder();
        for(char c: set) sb.append(c);
        return sb.toString();
    }

    // 8 Longest substring
    static int longestSubstring(String s){
        Set<Character> set = new HashSet<>();
        int l=0,max=0;
        for(int r=0;r<s.length();r++){
            while(set.contains(s.charAt(r))){
                set.remove(s.charAt(l++));
            }
            set.add(s.charAt(r));
            max = Math.max(max,r-l+1);
        }
        return max;
    }

    // 9 Digit check
    static boolean isDigits(String s){
        return s.matches("\\d+");
    }

    // 10 Capitalize
    static String capitalize(String s){
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String w: words){
            sb.append(Character.toUpperCase(w.charAt(0)))
              .append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    // 11 Second largest
    static int secondLargest(int[] arr){
        int first=Integer.MIN_VALUE, second=Integer.MIN_VALUE;
        for(int n: arr){
            if(n>first){ second=first; first=n; }
            else if(n>second && n!=first) second=n;
        }
        return second;
    }

    // 12 Missing number
    static int missingNumber(int[] arr, int n){
        int sum = n*(n+1)/2;
        for(int i: arr) sum -= i;
        return sum;
    }

    // 13 Duplicate
    static int findDuplicate(int[] arr){
        Set<Integer> set = new HashSet<>();
        for(int n: arr) if(!set.add(n)) return n;
        return -1;
    }

    // 14 Pair sum
    static void pairSum(int[] arr,int target){
        Set<Integer> set = new HashSet<>();
        for(int n: arr){
            if(set.contains(target-n))
                System.out.println(n+" "+(target-n));
            set.add(n);
        }
    }

    // 15 Move zeros
    static int[] moveZero(int[] arr){
        int idx=0;
        for(int n: arr) if(n!=0) arr[idx++]=n;
        while(idx<arr.length) arr[idx++]=0;
        return arr;
    }

    // 16 Min Max
    static void minMax(int[] arr){
        int min=arr[0], max=arr[0];
        for(int n: arr){
            min = Math.min(min,n);
            max = Math.max(max,n);
        }
        System.out.println("Min="+min+" Max="+max);
    }

    // 17 Intersection
    static Set<Integer> intersection(int[] a, int[] b){
        Set<Integer> set = new HashSet<>();
        for(int n:a) set.add(n);
        Set<Integer> res = new HashSet<>();
        for(int n:b) if(set.contains(n)) res.add(n);
        return res;
    }

    // 18 Kth largest
    static int kthLargest(int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int n: arr){
            pq.add(n);
            if(pq.size()>k) pq.poll();
        }
        return pq.peek();
    }
}