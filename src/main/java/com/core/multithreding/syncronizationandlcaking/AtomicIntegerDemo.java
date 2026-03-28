package com.core.multithreding.syncronizationandlcaking;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {

        AtomicInteger counter = new AtomicInteger(0);

        // Basic operations
        System.out.println(counter.get());               // 0
        System.out.println(counter.getAndIncrement());   // 0 (returns OLD)
        System.out.println(counter.incrementAndGet());   // 2 (returns NEW)
        System.out.println(counter.getAndAdd(5));        // 2 (returns OLD)
        System.out.println(counter.addAndGet(3));        // 10 (returns NEW)

        // CAS
        boolean ok = counter.compareAndSet(10, 100); // if 10, set to 100
        System.out.println("CAS(10->100): " + ok + " val=" + counter.get());
        ok = counter.compareAndSet(10, 200); // fails — current is 100
        System.out.println("CAS(10->200): " + ok + " val=" + counter.get());

        // Java 8+ functional updates
        counter.set(5);
        System.out.println(counter.updateAndGet(x -> x * x)); // 25
        System.out.println(counter.accumulateAndGet(3, Integer::max)); // 25

        // Concurrent counter test
        AtomicInteger atomicCount = new AtomicInteger(0);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++)
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++)
                    atomicCount.incrementAndGet();
            });
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Atomic result: " + atomicCount.get()); // 100000

        // AtomicReference
        AtomicReference<String> ref = new AtomicReference<>("hello");
        ref.set("world");
        boolean cas = ref.compareAndSet("world", "java");
        System.out.println("CAS: " + cas + " val=" + ref.get());

        // LongAdder — better than AtomicLong under high contention
        LongAdder adder = new LongAdder();
        Thread[] adderTs = new Thread[10];
        for (int i = 0; i < 10; i++)
            adderTs[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) adder.increment();
            });
        for (Thread t : adderTs) t.start();
        for (Thread t : adderTs) t.join();
        System.out.println("LongAdder: " + adder.sum()); // 100000
    }
}
