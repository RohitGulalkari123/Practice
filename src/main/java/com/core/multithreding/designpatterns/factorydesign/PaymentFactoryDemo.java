package com.core.multithreding.designpatterns.factorydesign;

public class PaymentFactoryDemo {
    public static void main(String[] args) {
        PaymentService service = PaymentFactory.getPayment("UPI");
        service.pay(1000);
    }
}
