package com.core.codinginterviewquestions.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread {
    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(()->{
            //1 to 100.
            for(int i=0;i<100;i++){



            }


        });

    }
}
