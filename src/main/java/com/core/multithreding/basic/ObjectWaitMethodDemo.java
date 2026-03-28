package com.core.multithreding.basic;

public class ObjectWaitMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        boolean[] ready = {false};
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                while (!ready[0]) {
                    try {
                        System.out.println("Waiter Releasing lock, waiting...");
                        lock.wait(); // releases lock, enters WAITING
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println("[Waiter] Condition met, proceeding!");
            }
        });

        Thread notifier = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            synchronized (lock) { // can get lock because waiter released it
                ready[0] = true;
                System.out.println("[Notifier] Calling notify()");
                lock.notify(); // wakes waiter: WAITING -> BLOCKED
            }
        });
        waiter.start();
        Thread.sleep(100);
        notifier.start();
        waiter.join();
        notifier.join();


    }
}
