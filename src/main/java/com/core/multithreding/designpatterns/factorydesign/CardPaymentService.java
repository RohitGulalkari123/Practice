package com.core.multithreding.designpatterns.factorydesign;

class CardPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via Card: " + amount);
    }
}