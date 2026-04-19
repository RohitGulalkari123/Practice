package com.core.multithreding.pubSubExample;

public class ProducrConsumerDemo {
    public static void main(String[] args) {
        SharedResource sr=new SharedResource(2);

        Thread producerTh=new Thread(()->{

            try {
                for (int i=1,j=5;i<=j;i++,j--) {
                    sr.produce(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Thread consumerTh=new Thread(()->{

            try {
                for (int i=1,j=5;i<=j;i++,j--) {
                    sr.consumer();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerTh.start();
        consumerTh.start();
    }
}
