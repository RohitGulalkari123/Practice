package com.core.capgimini.streams.javafeatures.streams.employees;

import com.core.streams.app01.CapgiminiEmployee;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandsOnPractice {
    public static void main(String[] args) {
        List<CapgiminiEmployee> extracted = extracted();

        //Q1 Find all IT employees
        List<CapgiminiEmployee> listOfITEmployee = extracted.stream()
                .filter(e -> "IT".equalsIgnoreCase(e.getDepartment()))
                .toList();
        System.out.println("listOfITEmployee : " + listOfITEmployee);

        List<String> list = listOfITEmployee.stream().map(CapgiminiEmployee::getName).toList();
        System.out.println("list : " + list);

        //Q6 Get all names
        Map<String, Long> mapDeptCount = extracted.stream()
                .collect(
                        Collectors.groupingBy(
                                CapgiminiEmployee::getDepartment,
                                Collectors.counting()
                        ));

        System.out.println("mapDeptCount : " + mapDeptCount);


        //Q17 Average salary per department
        Map<String, Double> deptPerAvgSalary = extracted
                .stream()
                .collect(
                        Collectors.groupingBy(
                                CapgiminiEmployee::getDepartment, Collectors.averagingDouble
                                        (CapgiminiEmployee::getSalary)
                        )
                );
        System.out.println("deptPerAvgSalary : " + deptPerAvgSalary);


        //5 Aggregation Questions
        //Q19 Total salary
        double sumOfAllEmplouyeesSalaries = extracted.stream().mapToDouble(CapgiminiEmployee::getSalary).sum();
        System.out.println("sumOfAllEmplouyeesSalaries :" + sumOfAllEmplouyeesSalaries);

        //Q20 Average salary
        OptionalDouble average = extracted.stream().mapToDouble(CapgiminiEmployee::getSalary).average();
        System.out.println("average :" + average);

        //Q23 Get all skills  unik ques
        List<String> allSkills = extracted.stream().flatMap(e -> e.getSkills().stream()).distinct().collect(Collectors.toList());
        System.out.println("allSkills : " + allSkills);

        //Q25 Count skill occurrence
        Map<String, Long> skillAndItsCount = extracted.stream().flatMap(e -> e.getSkills().stream()).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("skillAndItsCount : " + skillAndItsCount);


        //Q26 Most common skill
        Optional<Map.Entry<String, Long>> max = extracted.stream().flatMap(e -> e.getSkills().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue());
        System.out.println("Most Common Skills  : " + max.get());

        //Q28 Any employee age > 40
        boolean b = extracted.stream().anyMatch(e -> e.getAge() > 40);
        System.out.println("Any Employee Which asge greter than 40 ?  : " + b);


        //Q29 All employees salary > 50000
        boolean b1 = extracted.stream()
                .allMatch(
                        e ->
                                e.getSalary() > 50000
                );

        System.out.print("------>" + b1);


    }

    private static List<CapgiminiEmployee> extracted() {
        return List.of(
                new CapgiminiEmployee("Alice", "IT", 90000, 28, List.of("Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Bob", "HR", 55000, 35, List.of("Java", "Spring Boot", "Microservice", "Kafka")),
                new CapgiminiEmployee("Charlie", "IT", 80000, 32, List.of("Core Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Diana", "Finance", 95000, 40, List.of("Servlet", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Eve", "HR", 60000, 29, List.of("REST API", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Frank", "IT", 72000, 26, List.of("Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Grace", "Finance", 88000, 38, List.of("MONGODB", "Spring Boot", "Microservice"))
        );
    }
}
