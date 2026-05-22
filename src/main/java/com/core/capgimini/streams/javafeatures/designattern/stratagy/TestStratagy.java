package com.core.capgimini.streams.javafeatures.designattern.stratagy;

public class TestStratagy {
    public static void main(String[] args) {
        PaymentService paySer=new PaymentService(new UpiPayment());
        paySer.processPayment(100);
    }
}
