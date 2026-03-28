package com.core.multithreding.syncronizationandlcaking;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    // 1. Basic lock/unlock
    void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rl = new ReentrantLockDemo();
        rl.increment();
        int count = rl.getCount();
        System.out.println("count: " + count);
    }
}
