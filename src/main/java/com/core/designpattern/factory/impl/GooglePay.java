package com.core.designpattern.factory.impl;

import com.core.designpattern.factory.inter.PaymentProcessor;

public class GooglePay  implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Deducting amount from Google Pay .";
    }

    @Override
    public String getReceipt() {
        return "Google Pay Receipt ";
    }
}
