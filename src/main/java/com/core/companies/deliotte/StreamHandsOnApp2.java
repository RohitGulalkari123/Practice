package com.core.companies.deliotte;

import com.core.companies.deliotte.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class StreamHandsOnApp2 {
    public static void main(String arg[]) {
        List<Employee> employees = DataSet.getEmployees();

        //Q13. Find duplicate salaries
        Set<Double> seen = new HashSet<>();
        List<Double> duplicateSalaries =
                employees.stream()
                        .map(Employee::getSalary)
                        .filter(sal -> !seen.add(sal))
                        .distinct()
                        .toList();
        System.out.println("duplicateSalaries:" + duplicateSalaries);

//Q14. Find highest salary in each department
        Map<String, Optional<Employee>> highestSalaryByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                        ));
        System.out.println("highestSalaryByDept:" + highestSalaryByDept);

        //Q15. Convert list to Map (id → employee) Q15. Convert list to Map (id → employee)
        Map<Integer, Employee> empMap =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::getId,
                                e -> e
                        ));

        System.out.println("empMap:" + empMap);

        //empid and empname
        Map<Integer, String> mapIDToNaME = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
        System.out.println("mapIDToNaME:" + mapIDToNaME);


        //  Q16. Find department with highest average salary
        Map.Entry<String, Double> result = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        System.out.println("result:" + result);

        ///Q17. Get top 3 highest paid employees  ->sorted logic bhai
        employees.stream().sorted(
                        Comparator.comparing(
                                        Employee::getSalary
                                )
                                .reversed()
                ).limit(3)
                .forEach(System.out::println);

        // Q18. Any employee earns > 80,000
        boolean exists =
                employees.stream()
                        .anyMatch(e -> e.getSalary() > 80000);
        System.out.println("exists:" + exists);

        // Q19. All employees above 25
        boolean allAbove25 =
                employees.stream()
                        .allMatch(e -> e.getAge() > 25);
        System.out.println("allAbove25:" + allAbove25);

        //🔷 Q20. First employee from IT
        Employee emp =
                employees.stream()
                        .filter(e -> e.getDepartment().equals("IT"))
                        .findFirst()
                        .orElse(null);
        System.out.println("emp:" + emp);

        //🔷 Q22. Dept → Employee Names
        Map<String, List<String>> result2 =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.mapping(Employee::getName, Collectors.toList())
                        ));
        System.out.println("result:" + result2);

        //🔷 Q23. Minimum Salary Employee
        Employee minEmp =
                employees.stream()
                        .min(Comparator.comparing(Employee::getSalary))
                        .orElse(null);
        System.out.println("minEmp:" + minEmp);

        //🔷 Q24. Sum of All Salaries
        double total =
                employees.stream()
                        .mapToDouble(Employee::getSalary)
                        .sum();
        System.out.println("total:" + total);

//🔷 Q21. Group by Dept + Sort inside group
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

    }
}
