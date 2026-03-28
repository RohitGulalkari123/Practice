package com.core.multithreding.executorframework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo extends RecursiveTask<Integer> {
    int start, end;

    ForkJoinPoolDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {

        /** ForkJoinPool is designed for divide-and-conquer tasks. Uses work-stealing algorithm — idle threads
         steal tasks from TAIL of other threads' deques. Reduces contention vs. shared queue.


         Step 1: Start main task
         Task: sum(1–8)
         Too big → fork into (1–4) and (5–8)

         Step 2: Fork again
         Task: sum(1–4)
         Too big → fork into (1–2) and (3–4)
         Task: sum(5–8)
         Too big → fork into (5–6) and (7–8)

         Step 3: Small enough → compute directly
         sum(1–2) = 3
         sum(3–4) = 7
         sum(5–6) = 11
         sum(7–8) = 15

         sum(1–4) = 3 + 7 = 10
         sum(5–8) = 11 + 15 = 26

         Step 5: Final join
         sum(1–8) = 10 + 26 = 36
         **/

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinPoolDemo task = new ForkJoinPoolDemo(1, 8);
        int result = pool.invoke(task);

        System.out.println(result); // 36
    }

    @Override
    protected Integer compute() {
     // if small task
        if (end - start <= 1) {
            int sum = 0;
            for (int i = start; i <= end; i++)
                sum += i;
            return sum;
        }

        // fork tasks
        int mid = (start + end) / 2;
        ForkJoinPoolDemo left = new ForkJoinPoolDemo(start, mid);
        ForkJoinPoolDemo right = new ForkJoinPoolDemo(mid + 1, end);

        left.fork();              // run in parallel
        int rightResult = right.compute(); // compute directly
        int leftResult = left.join();      // wait for left

        return leftResult + rightResult;
    }
}
