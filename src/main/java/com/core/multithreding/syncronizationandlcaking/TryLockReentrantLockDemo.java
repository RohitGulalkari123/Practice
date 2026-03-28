package com.core.multithreding.syncronizationandlcaking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread holder = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Holder Holding lock for 2s...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        });

        holder.start();
        Thread.sleep(200);

        // Non-blocking try
        boolean got = lock.tryLock();
        System.out.println("tryLock() immediate: " + got); // false

        // Timed try
        got = lock.tryLock(3, TimeUnit.SECONDS);
        if (got) {
            try { System.out.println("Got lock after waiting!"); }
            finally { lock.unlock(); }
        }
        holder.join();
    }
}
