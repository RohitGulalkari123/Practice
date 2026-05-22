package com.core.streams.app01.interviewPrep;

import com.core.streams.app01.CapgiminiEmployee;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewQue {
    public static void main(String[] args) {
        List<CapgiminiEmployee> empLst = List.of(
                new CapgiminiEmployee("Alice", "IT", 90000, 28, List.of("Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Bob", "HR", 55000, 35, List.of("Java", "Spring Boot", "Microservice", "Kafka")),
                new CapgiminiEmployee("Charlie", "IT", 80000, 32, List.of("Core Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Diana", "Finance", 95000, 40, List.of("Servlet", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Eve", "HR", 60000, 29, List.of("REST API", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Frank", "IT", 72000, 26, List.of("Java", "Spring Boot", "Microservice")),
                new CapgiminiEmployee("Grace", "Finance", 88000, 38, List.of("MONGODB", "Spring Boot", "Microservice"))
        );

        //  Q1.  Find all employees whose name starts with 'A'.
        empLst.stream().filter(e -> e.getName().startsWith("A")).forEach(System.out::println);

        //Q2.  Group employees by department.
        Map<String, List<CapgiminiEmployee>> grpByEmmp = empLst.stream().collect(Collectors.groupingBy(CapgiminiEmployee::getDepartment));
        System.out.println("grpByEmmp : " + grpByEmmp);

        //   Q3.  Find the department with the maximum number of employees.
        //Grouping By Map then check the count using entrySet
        empLst.stream()
                .collect(
                        Collectors.groupingBy(CapgiminiEmployee::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);

        //Q4.  Find the average salary per department.

        Map<String, Double> avgByDept = empLst.stream().collect(Collectors.groupingBy(CapgiminiEmployee::getDepartment, Collectors.averagingDouble(CapgiminiEmployee::getSalary)));
        System.out.println("avgByDept : " + avgByDept);


        //Q5.  Find the employee with the highest salary in each department.
        Map<String, Optional<CapgiminiEmployee>> topByDept = empLst.stream().collect(
                Collectors.groupingBy(CapgiminiEmployee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(CapgiminiEmployee::getSalary))));
        System.out.println("topByDept : " + topByDept);

        //Q6.  Sort employees by salary.
        // Ascending
        List<CapgiminiEmployee> sorted = empLst.stream().sorted(Comparator.comparingDouble(CapgiminiEmployee::getSalary)).toList();
        System.out.println("Sort employees by salary : " + sorted);
        // Descending
        List<CapgiminiEmployee> descOorted = empLst.stream().sorted(Comparator.comparingDouble(CapgiminiEmployee::getSalary).reversed()).toList();
        System.out.println("descOorted employees by salary : " + descOorted);

        //Q7.  Find the second-highest salary.
        empLst.stream().map(CapgiminiEmployee::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(
                1
        ).findFirst().ifPresent(System.out::println);

        // Q8.  Calculate sum of all even numbers in a list.
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int evenSum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("evenSum : " + evenSum);
//sum of all odd no
        int oddSum = numbers.stream().filter(n -> n % 2 != 0).mapToInt(Integer::intValue).sum();
        System.out.println("oddSum : " + oddSum);


        // Q9.  Remove duplicate elements from a list using streams.

        List<String> uniqueEmpLst = empLst.stream().map(CapgiminiEmployee::getName).distinct().toList();
        System.out.println("uniqueEmpLst : " + uniqueEmpLst);

        // Maintain insertion order and remove dupes using LinkedHashSet

        List<String> uniqueOrdered = uniqueEmpLst.stream()
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream()
                .collect(Collectors.toList());
        System.out.println("uniqueOrdered : " + uniqueOrdered);


        //  Q10.  Count strings in a list that start with a specific letter.
        List<String> names = List.of("Alice", "Adam", "Bob", "Charlie", "Aaron", "Bella", "Albela");
        long countA = names.stream()
                .filter(s -> s.startsWith("A"))
                .count();

        System.out.println("countA : " + countA);

        //Q11.  Sort a list of strings alphabetically (ascending and descending).

        List<String> asc = names.stream().sorted().collect(Collectors.toList());
        System.out.println("asc  ::" + asc);
        List<String> desc = names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("desc ::" + desc);


        List<String> caseInsensitive = names.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        System.out.println("caseInsensitive ::" + caseInsensitive);
// find avg by male and female
        List<Student> students = List.of(
                new Student("Alice", "Female", 22),
                new Student("Bob", "Male", 25),
                new Student("Charlie", "Male", 23),
                new Student("Diana", "Female", 24),
                new Student("Eve", "Female", 21),
                new Student("Frank", "Male", 26)
        );

        Map<String, Double> avgStudentAgeMap = students.stream().collect(Collectors.groupingBy(Student::gender, Collectors.averagingInt(Student::age)));
        System.out.println("avgStudentAgeMap : " + avgStudentAgeMap);

      //  Q16.  Find sum using mapToInt() and sum().

        int totalSalary = empLst.stream()
                .mapToInt(e -> e.getSalary())
                .sum();

        System.out.println("totalSalary : " + totalSalary);




    }
}
