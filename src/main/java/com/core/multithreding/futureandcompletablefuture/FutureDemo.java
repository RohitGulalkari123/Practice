package com.core.multithreding.futureandcompletablefuture;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        // Basic Future usage
        Future<Integer> future = exec.submit(
                () -> {
                    Thread.sleep(500);
                    return 42;
                }
        );

        System.out.println("isDone before: " + future.isDone()); // false
        int result = future.get(); // BLOCKS until done
        System.out.println("isDone after: " + future.isDone());  // true
        System.out.println("Result: " + result);                 // 42

        // Timed get — avoids indefinite blocking
        Future<String> slow = exec.submit(() -> {
            Thread.sleep(3000);
            return "slow result";
        });
        try {
            String r = slow.get(1, TimeUnit.SECONDS); // TimeoutException
        } catch (TimeoutException e) {
            System.out.println("Timed out! Cancelling...");
            slow.cancel(true); // interrupt the thread
        }

        // Exception in task — wrapped as ExecutionException
        Future<Integer> bad = exec.submit(() -> {
            throw new RuntimeException("Task failed!");
        });
        try {
            bad.get();
        } catch (ExecutionException e) {
            System.out.println("Caught: " + e.getCause().getMessage());
        }

        // Parallel tasks then aggregate (classic Future pattern)
        Future<Long> t1 = exec.submit(() -> computeSum(1, 500_000));
        Future<Long> t2 = exec.submit(() -> computeSum(500_001, 1_000_000));
        long total = t1.get() + t2.get(); // each get() blocks
        System.out.println("Parallel sum: " + total);
        exec.shutdown();
    }

    static long computeSum(long from, long to) {
        long sum = 0;
        for (long i = from; i <= to; i++) sum += i;
        return sum;
    }
}
