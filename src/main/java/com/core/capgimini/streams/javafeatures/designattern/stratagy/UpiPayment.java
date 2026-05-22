package com.core.capgimini.streams.javafeatures.designattern.stratagy;

public class UpiPayment implements PaymentStratagy {
    @Override
    public void pay(int amount) {
        System.out.println("UPI Payment Strategy pay " + amount);
    }
}
