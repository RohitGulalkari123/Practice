package com.core.companies.deliotte;

import com.core.companies.deliotte.model.Employee;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class RePracticeAllExample2 {
    public static void main(String[] args) {
        List<Employee> employees = getEmployees();



    }

    @NotNull
    private static List<Employee> getEmployees() {
        return DataSet.getEmployees();
    }
}
