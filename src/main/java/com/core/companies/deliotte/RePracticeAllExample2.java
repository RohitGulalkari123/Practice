package com.core.companies.deliotte;

import com.core.companies.deliotte.model.Employee;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RePracticeAllExample2 {
    public static void main(String[] args) {
        List<Employee> employees = getEmployees();

        //Get all skills of all employees (flatten list)
        List<String> uniqueSkillList = employees.stream().flatMap(e -> e.getSkills().stream()).distinct().toList();
        System.out.println("uniqueSkillList: " + uniqueSkillList);

        //Get distinct skills across company  :-> dept and thir skillsed  map
        Map<String, Set<String>> mapOfDeptSkills = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.flatMapping(
                e -> e.getSkills().stream(), Collectors.toSet()
        )));
        System.out.println("mapOfDeptSkills: " + mapOfDeptSkills);

        // Count total number of skills in company
        long count = employees.stream().flatMap(
                e -> e.getSkills().stream()
        ).distinct().count();
        System.out.println("Total Unique Skills count: " + count);

        //  Find employees who have "Java" skill
        Employee javaFirstEmployee = employees.stream()
                .filter(
                        e -> e.getSkills().stream()
                                .anyMatch(s -> s.equalsIgnoreCase("Java")))
                .findFirst()
                .orElseThrow();
        System.out.println("Java First Employee: " + javaFirstEmployee);

        //  Get top 3 most common skills
        Map<String, Long> topSkills =
                employees.stream()
                        .flatMap(e -> e.getSkills().stream())
                        .collect(Collectors.groupingBy(
                                skill -> skill,
                                Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(3)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));
        System.out.println("topSkills: " + topSkills);









    }

    @NotNull
    private static List<Employee> getEmployees() {
        return DataSet.getEmployees();
    }
}
