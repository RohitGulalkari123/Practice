package com.core.capgimini.streams.javafeatures.exceptionhandling;

public class TestException {
    public static void main(String[] args) {
        int a=0;
        int b=9;
        int result=0;
        try {
            result= Integer.parseInt(null);
           System.out.println(result);
        }catch (AcceptorException e) {
            throw new AcceptorException(e.getMessage());
        }
    }
}
