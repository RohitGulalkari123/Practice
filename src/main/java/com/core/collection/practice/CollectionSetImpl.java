package com.core.collection.practice;

import java.util.*;

public class CollectionSetImpl {
    public static void main(String[] args) {
        // HashSet — Remove Duplicates from List
        List<String> raw = Arrays.asList(
                "apple", "banana", "apple", "cherry", "banana");
        Set<String> unique = new HashSet<>(raw);
        System.out.println(unique);
        System.out.println("Size: " + unique.size());
        System.out.println(unique.contains("apple"));

        //TreeSet — Sorted Scores (with navigation)
        TreeSet<Integer> s = new TreeSet<>();
        s.add(85);
        s.add(92);
        s.add(78);
        s.add(92); // dup ignored
        System.out.println(s); // [78, 85, 92]
        System.out.println(s.first()); // 78
        System.out.println(s.last()); // 92
        System.out.println(s.headSet(90)); // [78, 85] (below 90)
        System.out.println(s.tailSet(85)); // [85, 92] (from 85 incl)
        System.out.println(s.subSet(80, 92)); // [85]
        System.out.println(s.floor(88)); // 85 (greatest <= 88)

        //Set Operations — Union, Intersection, Difference
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        Set<Integer> inter = new HashSet<>(a);
        inter.retainAll(b);
        Set<Integer> diff = new HashSet<>(a);
        diff.removeAll(b);
        System.out.println("Union: " + union); // [1,2,3,4,5,6]
        System.out.println("Intersection: " + inter); // [3,4]
        System.out.println("Difference: " + diff); // [1,2]




    }

}
