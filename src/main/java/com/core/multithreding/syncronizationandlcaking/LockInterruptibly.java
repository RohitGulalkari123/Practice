package com.core.multithreding.syncronizationandlcaking;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibly {
   public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread holder = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        });
        Thread waiter = new Thread(() -> {
            try {
                lock.lockInterruptibly(); // can be interrupted while waiting
                try {
                    System.out.println("[Waiter] Got lock");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("[Waiter] Interrupted while waiting");
            }
        });

        holder.start();
        Thread.sleep(100);
        waiter.start();
        Thread.sleep(300);
        waiter.interrupt(); // interrupt the waiting thread
        holder.interrupt();
        holder.join();
        waiter.join();
    }
}
