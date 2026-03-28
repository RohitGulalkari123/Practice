package com.core.multithreding.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadsJava21Demo {

    /**
     * A virtual thread is like a tiny lightweight worker
     * Very cheap (can create thousands)
     * Managed by Java (not heavy like real threads)
     * Great for waiting tasks (like API calls, DB calls)
     * Virtual Thread Executor = A manager that gives every task its own tiny lightweight thread
     * Give each task its own virtual thread
     **/
    public static void main(String[] args) {

        ExecutorService executor = Executors.newVirtualThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task " + taskId + " finished");
            });
        }


    }
}
