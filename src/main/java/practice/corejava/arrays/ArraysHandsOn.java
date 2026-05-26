package practice.corejava.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArraysHandsOn {
    public static void main(String[] args) {
        //1. Find Duplicate Elements
        duplicatesRecords();
        //2. Find Missing Number
        findMissingNo();
        //3. Two Sum Problem
        findTwoSum();
//4. Second Largest Number
        secondLargetNo();
    }

    private static void secondLargetNo() {
        int arr[] =
                {2, 7, 11, 15};
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int a : arr) {
            if (a > first) {
                second=first;
                first=a;
            } else if (a > second && a != first ) {
                second = a;
            }
        }
        System.out.println("Min value is :"+first + "  Maximum value is :" + second);
    }

    private static void findTwoSum() {
        int arr[] =
                {2, 7, 11, 15};

        Map<Integer, Integer>
                map =
                new HashMap<>();

        int target = 9;

        for (int n : arr) {

            int need =
                    target - n;

            if (map.containsKey(
                    need)) {

                System.out.println(" Taget of " + target + " achieved : " +
                        n + " & " + need);

            }

            map.put(n, 1);

        }
        //System.out.println("map:" + map);
    }

    private static void findMissingNo() {
        int arr[] =
                {1, 2, 4};
        int n = 4;
        int expected =
                n * (n + 1) / 2;
        int actual = 0;

        for (int x : arr) {
            actual += x;
        }

        System.out.println(
                expected - actual);
    }

    private static void duplicatesRecords() {
        int arr[] =
                {1, 2, 3, 2, 5};
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(i)) {
                System.out.println("Duplicates found set contains " + i);
            }
            set.add(i);
        }
    }
}
