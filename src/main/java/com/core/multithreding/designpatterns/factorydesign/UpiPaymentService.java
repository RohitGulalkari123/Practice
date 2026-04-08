package com.core.multithreding.designpatterns.factorydesign;

class UpiPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via UPI: " + amount);
    }
}

