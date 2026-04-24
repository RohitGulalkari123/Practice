package com.core.designpattern.factory.inter;

public interface PaymentProcessor {
    String processPayment(double amount);
    String getReceipt();
}