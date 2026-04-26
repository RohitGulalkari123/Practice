package com.core.companies.deliotte.model;

class Department {
    int id;
    String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}