package com.core.companies.deliotte;

import com.core.companies.deliotte.model.Employee;
import com.core.companies.deliotte.model.Project;

import java.util.*;

public class DataSet {
    public static List<Employee> getEmployees() {

        return Arrays.asList(

            new Employee(1, "Rohit", "IT", 60000, 28,
                Arrays.asList("Java", "Spring", "SQL"),
                Arrays.asList(new Project("P1", 12), new Project("P2", 6))),

            new Employee(2, "Amit", "HR", 40000, 32,
                Arrays.asList("Recruitment", "Excel"),
                Arrays.asList(new Project("P3", 4))),

            new Employee(3, "Neha", "IT", 75000, 26,
                Arrays.asList("Java", "Microservices"),
                Arrays.asList(new Project("P4", 10), new Project("P5", 8))),

            new Employee(4, "Pooja", "Finance", 50000, 30,
                Arrays.asList("Accounting"),
                Collections.emptyList()), // no projects

            new Employee(5, "Rahul", "IT", 60000, 35,
                Arrays.asList("Java", "AWS"),
                Arrays.asList(new Project("P6", 14))),

            new Employee(6, "Sneha", "HR", 45000, 29,
                Arrays.asList("Recruitment", "Communication"),
                Arrays.asList(new Project("P7", 3))),

            new Employee(7, "Karan", "Finance", 80000, 40,
                Arrays.asList("Investment", "Excel"),
                Arrays.asList(new Project("P8", 18), new Project("P9", 6))),

            new Employee(8, "Ankit", "IT", 30000, 24,
                Arrays.asList("Support"),
                Collections.emptyList()),

            new Employee(9, "Priya", "HR", 70000, 31,
                Arrays.asList("Management", "Excel"),
                Arrays.asList(new Project("P10", 5))),

            new Employee(10, "Vikas", "Finance", 90000, 45,
                Arrays.asList("Audit", "Tax"),
                Arrays.asList(new Project("P11", 20))),

            // 🔥 Edge cases
            new Employee(11, "Rohit Jain", "IT", 60000, 28,   // duplicate name + salary
                Arrays.asList("Java"),
                Arrays.asList(new Project("P12", 2))),

            new Employee(12, "EmptyGuy", "IT", 20000, 22,
                Collections.emptyList(),  // no skills
                Collections.emptyList()) // no projects
        );
    }
}