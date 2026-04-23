package com.core.companies.deliotte;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamHandsOn {
    public static void main(String arg[]) {
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

        List<String> empNames = employees.stream().map(Employee::getName).toList();
        System.out.println("empNames:" + empNames);

        //employee names with salary more than 50000
        List<String> empWithSlaryMretHan50K = employees.stream().filter(e -> e.getSalary() > 50000).map(Employee::getName).toList();
        System.out.println("empWithSlaryMretHan50K :" + empWithSlaryMretHan50K);

//Q3. Count number of employees
        Long empCount = employees.stream().count();
        System.out.println("empCount:" + empCount);

        //Q4. Get distinct departments
        employees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        //get dept and employee count
        Map<String, List<Employee>> empWithDep = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("empWithDep:" + empWithDep);

        Map<String, Long> deptEmpCount = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("deptEmpCount:" + deptEmpCount);

        // Q5. Sort employees by salary (ascending)
        //salary by sorted manner
        employees.stream().map(Employee::getSalary).distinct().sorted().forEach(System.out::println);

        // Q5. Sort employees by salary (ascending)
        employees.stream().sorted(Comparator.comparing(Employee::getSalary)).toList().forEach(System.out::println);

        System.out.println("---------------------------------:");

        // Q5. Sort employees by salary (descending)
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).toList().forEach(System.out::println);

        //Q6. Find highest paid employee
        Employee empHighestSal = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println("empHighestSal:" + empHighestSal);

        //     Q7. Find second highest salary
        Employee secondHighSalaryemployee = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst().orElse(null);
        System.out.println("secondHighSalaryemployee:" + secondHighSalaryemployee);

        //  Q8. Group employees by department
        Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("collect:" + collect);


        //map of dept vrs empNames
        Map<String, List<String>> collectDeptWithEMplNames = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.print("collectDeptWithEMplNames :" + collectDeptWithEMplNames);


        //   Q9. Count employees in each department
        Map<String, Long> collect1 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("collect1:" + collect1);

        // Q10. Get average salary
        Map<String, Double> averageSalariedPerDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("averageSalariedPerDepartment:" + averageSalariedPerDepartment);

        // Q11. Get names of employees whose name starts with "A"
        List<String> employeeNamesStartWithA =
                employees.stream()
                        .map(Employee::getName)
                        .filter(name -> name.startsWith("A"))
                        .toList();
        System.out.println("employeeNamesStartWithA:" + employeeNamesStartWithA);

        Stream<String> employeeNameStartWithA = employees.stream().map(Employee::getName).filter(name -> name.startsWith("A"));
        System.out.println("employeeNameStartWithA:" + employeeNameStartWithA);

        //    Q12. Find employees older than 30 sorted by age
        List<Employee> empOlderTan30 = employees.stream().filter(e -> e.getAge() > 30).sorted(Comparator.comparing(Employee::getAge)).toList();
        System.out.println("empOlderTan30:" + empOlderTan30);

        List<String> empNamesOlder = employees.stream().filter(e -> e.getAge() > 30).sorted(Comparator.comparing(Employee::getAge)).map(Employee::getName).toList();
        System.out.println("empNamesOlder:" + empNamesOlder);

       // 👉 Q14 – Highest salary per department
    }
}
