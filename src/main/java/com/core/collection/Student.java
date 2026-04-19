package com.core.collection;

class Student implements Comparable<Student> {
    String name;
    int marks;

    Student(String n, int m) {
        name = n;
        marks = m;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.marks, o.marks); // asc by marks    }
    }

    public String toString() {
        return name + "(" + marks + ")";

    }
}