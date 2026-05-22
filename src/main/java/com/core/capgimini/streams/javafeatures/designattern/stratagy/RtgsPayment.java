package com.core.capgimini.streams.javafeatures.designattern.stratagy;

public class RtgsPayment implements PaymentStratagy {
    @Override
    public void pay(int amount) {
        System.out.println("RTGS Payment Strategy pay " + amount);
    }
}
