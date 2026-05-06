package com.core.collection;

import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

public class Deliotte {
    public static void main(String[] args) {

        //0 1 1 2
        List<Integer> list = IntStream.rangeClosed(1, 100).filter(i -> i%1 == i && i % i == 0).boxed().toList();
        System.out.println(list);

        IntStream.range(1,5);

                //prime no 1-100
        IntStream.rangeClosed(1,100).filter(i->1%1==0 && i%2==0).boxed().toList();
    }
}
