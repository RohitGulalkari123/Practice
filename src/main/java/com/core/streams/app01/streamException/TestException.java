package com.core.streams.app01.streamException;


import java.util.List;
import java.util.stream.Collectors;

import static com.core.streams.app01.streamException.ThrowingFunction.wrap;

public class TestException {
    public static void main(String[] args) {
        List<String> processed = List.of("").stream()
                .map(wrap(name -> processName(name)))
                .collect(Collectors.toList());


    }

    public static String processName(String name) {
        System.out.println("Throwing Exception :" + name);
        return "Exception Catched ";
    }
}
