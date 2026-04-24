package com.core.designpattern.factory.impl;

import com.core.designpattern.factory.inter.PaymentProcessor;

public class PayTm implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Deducting amount from PayTm Pay .";
    }

    @Override
    public String getReceipt() {
        return "PayTm Pay Receipt ";
    }
}