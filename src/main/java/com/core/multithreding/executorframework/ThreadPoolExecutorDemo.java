package com.core.multithreding.executorframework;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. Fixed thread pool
        // corePoolSize=3, maxPoolSize=3, unbounded LinkedBlockingQueue
        // RISK: unbounded queue can cause OOM under load
        ExecutorService fixed = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 6; i++) {
            final int id = i;
            fixed.submit(() -> {
                System.out.println("Task " + id + " on "
                        + Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                }
            });
        }
        fixed.shutdown();
        fixed.awaitTermination(5, TimeUnit.SECONDS);

        // 2. Custom ThreadPoolExecutor — production-ready
        ThreadPoolExecutor custom = new ThreadPoolExecutor(
                2,                                   // core = 2 always-alive
                4,                                   // max = 4
                30L, TimeUnit.SECONDS,               // idle threads above core die
                new ArrayBlockingQueue<>(5),          // BOUNDED queue — prevents OOM
                r -> new Thread(r, "Pool-" + System.nanoTime()), // custom names
                new ThreadPoolExecutor.CallerRunsPolicy() // backpressure on caller
        );
        for (int i = 0; i < 8; i++) {
            final int id = i;
            try {
                custom.submit(() -> {
                    System.out.println("Task " + id
                            + " pool=" + custom.getPoolSize()
                            + " active=" + custom.getActiveCount()
                            + " queue=" + custom.getQueue().size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("Task " + id + " REJECTED!");
            }
        }
        custom.shutdown();
        custom.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Completed: " + custom.getCompletedTaskCount());

        // submit() — returns Future, exception stored inside
        // execute() — void, exceptions go to UncaughtExceptionHandler (LOST!)

        // shutdown()    — no new tasks, waits for existing to complete
        // shutdownNow() — interrupts running tasks, returns pending
    }
}
