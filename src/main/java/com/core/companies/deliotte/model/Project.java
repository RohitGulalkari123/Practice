package com.core.companies.deliotte.model;

public class Project {
    String name;
    int duration; // months
    public Project(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() { return name; }
    public int getDuration() { return duration; }

    @Override
    public String toString() {
        return name + "(" + duration + "m)";
    }
}