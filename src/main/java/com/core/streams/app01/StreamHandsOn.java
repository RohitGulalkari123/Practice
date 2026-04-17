package com.core.streams.app01;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamHandsOn {
    public static void main(String[] args) {
        List<Tcs_Employee> employees = List.of(
                new Tcs_Employee("Alice", "IT", 90000, 28, List.of("Java", "Spring Boot", "Microservice")),
                new Tcs_Employee("Bob", "HR", 55000, 35, List.of("Java", "Spring Boot", "Microservice", "Kafka")),
                new Tcs_Employee("Charlie", "IT", 80000, 32, List.of("Core Java", "Spring Boot", "Microservice")),
                new Tcs_Employee("Diana", "Finance", 95000, 40, List.of("Servlet", "Spring Boot", "Microservice")),
                new Tcs_Employee("Eve", "HR", 60000, 29, List.of("REST API", "Spring Boot", "Microservice")),
                new Tcs_Employee("Frank", "IT", 72000, 26, List.of("Java", "Spring Boot", "Microservice")),
                new Tcs_Employee("Grace", "Finance", 88000, 38, List.of("MONGODB", "Spring Boot", "Microservice"))
        );

        // GROUPING BY DEPT

        Map<String, List<Tcs_Employee>> employeeByDept = employees.stream().collect(Collectors.groupingBy(Tcs_Employee::getDepartment));
        System.out.println(employeeByDept);
        System.out.println("IT Employee :" + employeeByDept.get("IT"));

        Map<String, Long> empCountByDept = employees.stream().collect(Collectors.groupingBy(Tcs_Employee::getDepartment, Collectors.counting()));
        System.out.println("empCountByDept is :" + empCountByDept);

        //partition By
        Map<Boolean, List<Tcs_Employee>> mapPartionBy = employees.stream().collect(Collectors.partitioningBy(te -> te.getDepartment().equalsIgnoreCase("IT")));
        System.out.println("mapPartionBy IT Employee :" + mapPartionBy.get(true));
        System.out.println("mapPartionBy NON IT Employee :" + mapPartionBy.get(false));

        //Exception in Stream
        employees.stream()
                .map(name -> {
                    try {
                        return processName(name); // may throw checked exception
                    } catch (Exception e) {
                        return "ERROR:" + name;  // fallback value
                    }
                })
                .collect(Collectors.toList());


    }

    public static String processName(Tcs_Employee name) {
        System.out.println("Throwing Exception :" + name);
        return "Exception Catched ";
    }

}
