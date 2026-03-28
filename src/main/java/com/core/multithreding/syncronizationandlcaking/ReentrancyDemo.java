package com.core.multithreding.syncronizationandlcaking;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrancyDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("Hold count: " + lock.getHoldCount()); // 1
            lock.lock(); // reentrant
            try {
                System.out.println("Hold count: " + lock.getHoldCount()); // 2
            } finally { lock.unlock(); }
            System.out.println("Hold count: " + lock.getHoldCount()); // 1
        } finally { lock.unlock(); }
        System.out.println("Hold count: " + lock.getHoldCount()); // 0
    }
}
