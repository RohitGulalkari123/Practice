package com.core.streams.app01.interviewPrep;

import com.core.streams.app01.CapgiminiEmployee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Comparison {
    public static void main(String[] args) {
        List<CapgiminiEmployee> employees = List.of(
                new CapgiminiEmployee("Alice", "IT", 90000, 28, List.of("Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Bob", "HR", 55000, 35, List.of("Java", "Spring Boot", "Microservice", "Kafka")),
                new CapgiminiEmployee("Charlie", "IT", 80000, 32, List.of("Core Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Diana", "Finance", 95000, 40, List.of("Servlet", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Eve", "HR", 60000, 29, List.of("REST API", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Frank", "IT", 72000, 26, List.of("Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Grace", "Finance", 88000, 38, List.of("MONGODB", "Spring Boot", "Microservice"))
        );

        //Comaparison Natural Order  --> comparing by age
        employees.stream().sorted(Comparator.comparing(CapgiminiEmployee::getAge)).toList().forEach(System.out::println);
        //comparing by salary

        System.out.println("-----Comparing by  Salary in descneding order high slaray is near --------");
        employees.stream().sorted(Comparator.comparing(
                CapgiminiEmployee::getSalary,
                Comparator.reverseOrder()
        )).toList().forEach(System.out::println);

        System.out.print("---Multi-level sort — by dept then by salary desc----");

        List<CapgiminiEmployee> multiSort = employees.stream()
                .sorted(Comparator.comparing(CapgiminiEmployee::getDepartment)
                        .thenComparingDouble(CapgiminiEmployee::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println("multiSort :;;" + multiSort);

    }
}
