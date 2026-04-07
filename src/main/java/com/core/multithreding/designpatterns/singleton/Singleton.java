package com.core.multithreding.designpatterns.singleton;

enum Singleton {
    INSTANCE;

    /**
     * Serialization safe
     * Reflection safe
     * JVM guarantees single instance
     **/

    public void doWork() {
        System.out.println("Working...");
    }
}