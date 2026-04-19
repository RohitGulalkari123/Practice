package com.core.multithreding.pubSubExample;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {

    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        this.sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer Is Full : Producer is waiting for consumer :");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Producer : " + item);
        notify();
    }

    public synchronized int consumer() throws InterruptedException {
        while (sharedBuffer.isEmpty()) {
            System.out.println("Buffer Is Empty : Consumer is waiting for Producer :");
            wait();
        }
        int itemCnt=sharedBuffer.poll();
        System.out.println("Consumer itemCnt : " + itemCnt);
        notify();
        return itemCnt;
    }

}
