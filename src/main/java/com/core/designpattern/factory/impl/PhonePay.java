package com.core.designpattern.factory.impl;


import com.core.designpattern.factory.inter.PaymentProcessor;

public class PhonePay implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Deducting amount from PhonePay Pay .";
    }

    @Override
    public String getReceipt() {
        return "PhonePay Pay Receipt ";
    }
}