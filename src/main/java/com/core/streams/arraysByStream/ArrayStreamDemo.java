package com.core.streams.arraysByStream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayStreamDemo {
    public static void main(String arg[]) {
        //Find the sum of all elements in an array
        System.out.println("1 Find the sum of all elements in an array");
        int[] arr = {1, 2, 3, 4, 5};
        int sum1 = Arrays.stream(arr).sum();
        int sum2 = Arrays.stream(arr).boxed().reduce(0, Integer::sum);
        int sum3 = Arrays.stream(arr).boxed().mapToInt(Integer::intValue).sum();
        System.out.println(sum1 + "," + sum2 + "," + sum3);

        System.out.println("2.Find maximum and minimum element in an array");
        int maxElement = Arrays.stream(arr).max().getAsInt();
        int minElement = Arrays.stream(arr).min().getAsInt();
        System.out.println(maxElement + "," + minElement);

        List<Integer> list = List.of(3, 1, 7, 9);
        list.stream().max(Integer::compareTo).get();

        //Find average of all elements in an array
        Double avg = Arrays.stream(arr).average().orElse(0.0);
        System.out.println("avg:" + avg);
        //IntSummerTy statistics
        IntSummaryStatistics iss = Arrays.stream(arr).summaryStatistics();
        System.out.println("iss:" + iss);
        System.out.println("count:" + iss.getCount());
        System.out.println("Sum:" + iss.getSum());
        System.out.println("Avg:" + iss.getAverage());
        System.out.println("Min:" + iss.getMin());
        System.out.println("Max:" + iss.getMax());

        // Remove duplicate elements from an array
        int[] uniqueArr = Arrays.stream(arr).distinct().toArray();
        for (int a : uniqueArr) {
            System.out.println("uniqueArr :" + a);
        }
        // Returns sorted distinct List
        List<Integer> sortedArayAsList = Arrays.stream(arr).distinct().sorted().boxed().toList();
        System.out.println("sortedArayAsList:" + sortedArayAsList);

        // From List<String>
        List<String> names = List.of("a", "b", "a", "c");
        List<String> unique = names.stream().distinct().toList();

        //Count even and odd numbers in an array
        List<Integer> evenList = Arrays.stream(arr).distinct().boxed().filter(a -> a % 2 == 0).toList();
        System.out.println("evenList:" + evenList);

        Long evenCnt = Arrays.stream(arr).filter(a -> a % 2 == 0).count();
        Long oddNoCnt = Arrays.stream(arr).filter(a -> a % 2 != 0).count();
        System.out.println("evenCnt:" + evenCnt);
        System.out.println("oddNoCnt:" + oddNoCnt);

// Partition in one pass using Collectors.partitioningBy
        // .boxed()  intStream to Stream<Integer>
        Map<Boolean, List<Integer>> partitioned =
                Arrays.stream(arr)
                        .boxed()
                        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        List<Integer> evens = partitioned.get(true);   // [2, 4, 6]
        List<Integer> odds = partitioned.get(false);  // [1, 3, 5]
        System.out.println("evens :" + evens + " odds :" + odds);

        //Reverse the array
        int[] revArr = {1, 2, 3, 4, 5};
        int [] reversedArray= IntStream.rangeClosed(1, revArr.length).map(i->arr[arr.length - i]).toArray();
        for(int a : reversedArray){
            System.out.println("reversedArray:" + a);

        }

    }
}
