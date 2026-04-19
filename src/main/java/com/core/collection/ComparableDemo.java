package com.core.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ComparableDemo {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("Raj", 75),
                new Student("Priya", 90),
                new Student("Amir", 82));
        Collections.sort(list);
        System.out.println(list); // [Raj(75), Amir(82), Priya(90)]
    }
}