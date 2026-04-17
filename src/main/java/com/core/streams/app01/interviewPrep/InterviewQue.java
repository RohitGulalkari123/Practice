package com.core.streams.app01.interviewPrep;

import com.core.streams.app01.Tcs_Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterviewQue {
    public static void main(String[] args) {
        List<Tcs_Employee> empLst = List.of(
                new Tcs_Employee("Alice", "IT", 90000, 28, List.of("Java", "Spring Boot", "Microservice")),
                new Tcs_Employee("Bob", "HR", 55000, 35, List.of("Java", "Spring Boot", "Microservice", "Kafka")),
                new Tcs_Employee("Charlie", "IT", 80000, 32, List.of("Core Java", "Spring Boot", "Microservice")),
                new Tcs_Employee("Diana", "Finance", 95000, 40, List.of("Servlet", "Spring Boot", "Microservice")),
                new Tcs_Employee("Eve", "HR", 60000, 29, List.of("REST API", "Spring Boot", "Microservice")),
                new Tcs_Employee("Frank", "IT", 72000, 26, List.of("Java", "Spring Boot", "Microservice")),
                new Tcs_Employee("Grace", "Finance", 88000, 38, List.of("MONGODB", "Spring Boot", "Microservice"))
        );

        //  Q1.  Find all employees whose name starts with 'A'.
        empLst.stream().filter(e -> e.getName().startsWith("A")).forEach(System.out::println);

        //Q2.  Group employees by department.
        Map<String, List<Tcs_Employee>> grpByEmmp = empLst.stream().collect(Collectors.groupingBy(Tcs_Employee::getDepartment));
        System.out.println("grpByEmmp : " + grpByEmmp);

        //   Q3.  Find the department with the maximum number of employees.
        //Grouping By Map then check the count using entrySet
        empLst.stream()
                .collect(
                        Collectors.groupingBy(Tcs_Employee::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);

        //Q4.  Find the average salary per department.

        Map<String, Double> avgByDept = empLst.stream().collect(Collectors.groupingBy(Tcs_Employee::getDepartment, Collectors.averagingDouble(Tcs_Employee::getSalary)));
        System.out.println("avgByDept : " + avgByDept);


        //Q5.  Find the employee with the highest salary in each department.
        Map<String, Optional<Tcs_Employee>> topByDept = empLst.stream().collect(
                Collectors.groupingBy(Tcs_Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Tcs_Employee::getSalary))));
        System.out.println("topByDept : " + topByDept);

        //Q6.  Sort employees by salary.
        // Ascending
        List<Tcs_Employee> sorted = empLst.stream().sorted(Comparator.comparingDouble(Tcs_Employee::getSalary)).toList();
        System.out.println("Sort employees by salary : " + sorted);
        // Descending
        List<Tcs_Employee> descOorted = empLst.stream().sorted(Comparator.comparingDouble(Tcs_Employee::getSalary).reversed()).toList();
        System.out.println("descOorted employees by salary : " + descOorted);

        //Q7.  Find the second-highest salary.
        Optional<Double> first = empLst.stream().mapToDouble(Tcs_Employee::getSalary).boxed().sorted(Comparator.reverseOrder()).distinct().skip(1)
                .findFirst();

        System.out.println("first : " + first.get());

    }
}
