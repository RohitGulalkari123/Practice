package com.core.companies.deliotte;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Employee employee = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println("employee ::" + employee);

        //  Find second highest salary employee
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst().ifPresent(System.out::println);
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).map(Employee::getName).findFirst().ifPresent(System.out::println);

        //  Find highest salary in each department
        Map<String, Optional<Employee>> collect2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)
                        )
                ));
        System.out.println(collect2);

        //  Find minimum salary employee
        Optional<Employee> min = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(min.get());

        //   Get top 3 highest paid employees
        List<Employee> top3EmpList = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).toList();
        System.out.println(top3EmpList);

        System.out.println("------------------------------------------------");

        //🔹 SORTING & ORDERING
        //  Sort employees by salary (ascending)  less salry to max salary
        for (Employee employee1 : employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).toList()) {
            System.out.println(employee1);
        }

        System.out.println("------------------------------------------------");

        //  Sort employees by salary (descending)
        for (Employee employee1 : employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).toList()) {
            System.out.println(employee1);
        }
        System.out.println("-----------------------Sorted By Age-------------------------");

        // Sort employees by age (for age > 30)
        for (Employee employee1 : employees.stream().sorted(Comparator.comparingDouble(Employee::getAge)).toList()) {
            System.out.println(employee1);
        }

        //  Group by department and sort employees inside each group
        Map<String, List<Employee>> grpByDeptSorted =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparing(Employee::getSalary))
                                                .toList()
                                )
                        ));
        System.out.println("grpByDeptSorted:" + grpByDeptSorted);


        // 🔹 FILTERING / MATCHING
        //Get employees whose name starts with "A"
        List<Employee> employeeList = employees.stream().filter(e -> e.getName().startsWith("A")).toList();
        System.out.println("employeeList :"+employeeList);

        // Find employees older than 30
        employeeList = employees.stream().filter(e -> e.getName().startsWith("A")).toList();

        // Check if any employee earns > 80,000
        boolean anyMatch=employees.stream().anyMatch(e->e.getSalary()>30000);
        System.out.println("anyMatch:"+anyMatch);

        // Check if all employees are above age 25
        boolean isAllEmployeeAbove25 = employees.stream().allMatch(e -> e.getSalary() > 25);
        System.out.println("isAllEmployeeAbove25:"+isAllEmployeeAbove25);

        // Find first employee from IT department
        employees.stream().filter(e ->e.getDepartment().equalsIgnoreCase("IT")).findFirst().ifPresent(System.out::println);

        //Convert List<Employee> → Map (id → employee)
        Map<Integer, Employee> collect3 = employees.stream().collect(Collectors.toMap(Employee::getId, e -> e));
        System.out.println("collect3:"+collect3);

        //Convert List<Employee> → Map (id → name)
        Map<Integer, String> collect4 = employees.stream().collect(Collectors.toMap(Employee::getId, e -> e.getName()));
        System.out.println("collect4:"+collect4);
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
