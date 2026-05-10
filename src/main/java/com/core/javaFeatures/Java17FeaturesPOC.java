package com.core.javaFeatures;// File: Java17FeaturesPOC.java

public class Java17FeaturesPOC {

    public static void main(String[] args) {

        /*
         * ============================================================
         * 1. Sealed Classes
         * ============================================================
         *
         * PROBLEM BEFORE:
         * Any class could extend parent class.
         * No restriction.
         *
         * JAVA 17:
         * Restrict inheritance.
         */

        Vehicle vehicle = new Car();
        vehicle.start();

        /*
         * ============================================================
         * 2. Pattern Matching for instanceof
         * ============================================================
         *
         * BEFORE:
         *
         * if(obj instanceof String){
         *      String s = (String)obj;
         * }
         *
         * JAVA 17:
         */

        Object obj = "Java17";

        if (obj instanceof String value) {
            System.out.println(value.toUpperCase());
        }

        /*
         * ============================================================
         * 3. Switch Expression
         * ============================================================
         *
         * BEFORE:
         * break keyword mandatory
         * fall-through issue
         *
         * JAVA 17:
         */

        int day = 2;

        String result = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            default -> "Invalid";
        };

        System.out.println(result);

        /*
         * ============================================================
         * 4. Text Blocks
         * ============================================================
         *
         * BEFORE:
         * Ugly string concatenation
         */

        String json = """
                {
                    "name":"Rohit",
                    "technology":"Java"
                }
                """;

        System.out.println(json);

        /*
         * ============================================================
         * 5. Records
         * ============================================================
         *
         * BEFORE:
         * Need getter/setter/constructor/toString/hashCode
         *
         * JAVA 17:
         * Immutable data carrier
         */

        Employee emp = new Employee(101, "Rohit");

        System.out.println(emp.id());
        System.out.println(emp.name());

        /*
         * ============================================================
         * 6. Helpful NullPointerException
         * ============================================================
         *
         * BEFORE:
         * NullPointerException without exact variable
         *
         * JAVA 17:
         * Better debugging
         */

        /*
        EmployeeData data = null;
        System.out.println(data.name.length());
        */

    }
}

/*
 * ============================================================
 * SEALED CLASS
 * ============================================================
 */

sealed class Vehicle permits Car, Bike {

    void start() {
        System.out.println("Vehicle Started");
    }
}

final class Car extends Vehicle {
}

final class Bike extends Vehicle {
}

/*
 * ============================================================
 * RECORD
 * ============================================================
 */

record Employee(int id, String name) {
}

/*
 * ============================================================
 * CLASS FOR NPE DEMO
 * ============================================================
 */

class EmployeeData {
    String name;
}