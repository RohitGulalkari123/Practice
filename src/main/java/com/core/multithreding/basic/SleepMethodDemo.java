package com.core.multithreding.basic;

public class SleepMethodDemo {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();
        Thread sleeper = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Sleeper Has lock, sleeping...");
                try {
                    Thread.sleep(2000);
                } // holds lock while sleeping!
                catch (InterruptedException e) {
                    System.out.println("[Sleeper] Interrupted");
                }
            }
        });

        Thread wanter = new Thread(() -> {
            System.out.println("Wanter Trying to get lock...");
            synchronized (lock) { // BLOCKED until sleeper releases
                System.out.println("[Wanter] Got lock!");
            }
        });

        sleeper.start();
        Thread.sleep(100);
        wanter.start();
        Thread.sleep(200);
        sleeper.interrupt();
        sleeper.join();
        wanter.join();

   }
}
