package com.core.capgimini.streams.javafeatures.streams.employees;

import java.util.List;

public class CapgiminiEmployee {
    private String name;
    private String department;
    private int salary;
    private int age;
    List<String> skills;

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public CapgiminiEmployee(String name, String department, int salary, int age, List<String> skills) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.skills = skills;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
