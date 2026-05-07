package com.core.collection.practice;

import java.util.*;

public class CollectionQueueDequeuImpl {
    public static void main(String[] args) {

        /**Queue = FIFO.
         * Deque = double-ended (supports both stack and queue).
         * PriorityQueue = heap-based,
         always polls the small**/

        //PriorityQueue — Task Scheduler POC
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0])
        );

        pq.offer(new int[]{3, 101}); // priority 3
        pq.offer(new int[]{1, 202}); // priority 1
        pq.offer(new int[]{2, 303}); // priority 2
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            System.out.println("Task " + t[1] + " P=" + t[0]);
        }

        //ArrayDeque as Stack (Undo System)
        Deque<String> undo = new ArrayDeque<>();
        undo.push("typed hello");//1
        undo.push("bold text");//2
        undo.push("inserted image");//3
        System.out.println(undo.pop());// Lst Element :Inserted Image
        System.out.println(undo.pop());
        System.out.println(undo.pop());

        //ArrayDeque as Queue (Print Queue)
        Queue<String> q = new ArrayDeque<>();
        q.offer("doc1.pdf");
        q.offer("report.pdf");
        q.offer("invoice.pdf");
        while (!q.isEmpty())
            System.out.println("Printing: " + q.poll());


    }
}
