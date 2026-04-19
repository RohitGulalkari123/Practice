package com.core.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("Raj", 75),
                new Student("Priya", 90),
                new Student("Amir", 82));
        Comparator<Student> comp =
                Comparator.comparing(s -> s.name);

        list.sort(comp);;
        System.out.println("By Name ::"+list);

        list.sort(Comparator.comparingInt(s -> s.marks)); // by marks asc
        System.out.println("By Marks ::"+list);

        list.sort(Comparator.comparingInt((Student s) -> s.marks)
                .reversed()); // by marks desc
        System.out.println("By Desc Marks ::"+list);

        list.sort(Comparator.comparing(s -> s.name)); // by name
        System.out.println("By Name ::"+list);

    }
}