package com.core.streams.app01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPocApp01 {
    private static Object list;

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000, 28),
                new Employee("Bob", "HR", 55000, 35),
                new Employee("Charlie", "IT", 80000, 32),
                new Employee("Diana", "Finance", 95000, 40),
                new Employee("Eve", "HR", 60000, 29),
                new Employee("Frank", "IT", 72000, 26),
                new Employee("Grace", "Finance", 88000, 38)
        );

        List<String> empList = employees.stream()
                .map(e -> e.getName().toUpperCase()).toList();
        System.out.println(
                "empList is :" + empList);

        // map() — transform salary (apply 10% hike)
        List<Double> hikedSalaries = employees.stream()
                .map(e -> e.getSalary() * 1.10)
                .collect(Collectors.toList());
        System.out.println("hikedSalaries is :" + hikedSalaries);

        // mapToInt() — convert to IntStream for math
        int totalAge = employees.stream()
                .mapToInt(Employee::getAge)
                .sum();
        System.out.println(
                "totalAge is :" + totalAge
        );

        // Example 1 — employees earning > 75000
        employees.stream().filter(e -> e.getSalary() > 5000).forEach(System.out::println);


        List<Employee> itSeniors = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .filter(e -> e.getAge() > 27)
                .collect(Collectors.toList());
        // [Alice(28), Charlie(32)]
        System.out.println(
                "itSeniors is :" + itSeniors
        );

        List<String> itSeniorsNames = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .filter(e -> e.getAge() > 27)
                .map(Employee::getName)
                .collect(Collectors.toList());
        // [Alice(28), Charlie(32)]
        System.out.println(
                "itSeniorsNames is :" + itSeniorsNames
        );

        List<String> aNames = employees.stream()
                .map(Employee::getName
                )
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(aNames);
// flatMap() — each employee has a LIST of skills → flatten all skills
        List<Developer> devs = List.of(
                new Developer("Alice", List.of("Java", "Kafka", "Docker")),
                new Developer("Charlie", List.of("Java", "Spring", "Redis")),
                new Developer("Frank", List.of("Kafka", "Kubernetes"))
        );

        // WITHOUT flatMap → Stream<List<String>> (nested)
        Stream<List<String>> nested = devs.stream().map(Developer::skills);
        System.out.println("nested : " + nested);

// WITH flatMap → Stream<String> (flat — all skills in one stream)
        List<String> allSkills = devs.stream()
                .flatMap(d -> d.skills().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
// [Docker, Java, Kafka, Kubernetes, Redis, Spring]
        System.out.println("allSkills:" + allSkills);

        List<Integer> numbers = IntStream.rangeClosed(1, 1_000_000)
                .boxed().collect(Collectors.toList());

        // Sequential
        long sumSeq = numbers.stream()
                .mapToLong(Integer::longValue).sum();
        System.out.println("sumSeq:" + sumSeq);



        

    }
}