package com.core.multithreding.designpatterns.singleton;

enum SingletonEnum {
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