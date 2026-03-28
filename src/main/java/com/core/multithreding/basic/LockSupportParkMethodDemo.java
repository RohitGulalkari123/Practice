package com.core.multithreding.basic;

import java.util.concurrent.locks.LockSupport;

public class LockSupportParkMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread parker = new Thread(() -> {
            System.out.println("Parker About to park...");
            LockSupport.park(); // no lock needed
            System.out.println("Parker Was unparked!");
        });
        parker.start();
        Thread.sleep(300);
        System.out.println("Main Calling unpark");
        LockSupport.unpark(parker); // wake specific thread
        parker.join();
    }
}
