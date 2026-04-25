package com.core.companies.deliotte;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RePracticeAllExample {
    public static void main(String[] args) {

        List<Employee> employees = getEmployees();

        //Get all employee names
        List<String> empNames = employees.stream().map(Employee::getName).toList();
        System.out.println(empNames);
        //Get employee names with salary > 50,000
        List<String> empNames2 = employees.stream().filter(e -> e.getSalary() > 50000).map(Employee::getName).toList();
        System.out.println(empNames2);
        //Count total number of employees
        Long empCount = employees.stream().count();
        System.out.println(empCount);
        //Get distinct departments
        List<String> deptList = employees.stream().map(Employee::getDepartment).distinct().toList();
        System.out.println(deptList);

        //  Group employees by department
        Map<String, List<Employee>> deptEmpList = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(deptEmpList);

        //  Count employees in each department
        Map<String, Long> collect = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(collect);
        //  Get department → employee names mapping
        Map<String, List<String>> collect1 = employees.stream().
                collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName,
                                Collectors.toList())));
        System.out.println(collect1);
        //   Get average salary per department
        Map<String, Double> deptAvgSalaries = employees.stream().
                collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(deptAvgSalaries);
        //  Find department with highest average salary
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);

        // Find highest paid employee
        //  Find second highest salary employee
        //  Find highest salary in each department
        //  Find minimum salary employee
        //   Get top 3 highest paid employees
    }

    @NotNull
    private static List<Employee> getEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Rohit", "IT", 60000, 28),
                new Employee(2, "Amit", "HR", 40000, 32),
                new Employee(3, "Neha", "IT", 75000, 26),
                new Employee(4, "Pooja", "Finance", 50000, 30),
                new Employee(5, "Rahul", "IT", 60000, 35),
                new Employee(6, "Sneha", "HR", 45000, 29),
                new Employee(7, "Karan", "Finance", 80000, 40),
                new Employee(8, "Ankit", "IT", 30000, 24),
                new Employee(9, "Priya", "HR", 70000, 31),
                new Employee(10, "Vikas", "Finance", 90000, 45)
        );
        return employees;
    }
}
