package com.core.multithreding.designpatterns.factorydesign;

class PaymentFactory {
    public static PaymentService getPayment(String type) {
        if (type.equalsIgnoreCase("UPI")) {
            return new UpiPaymentService();
        } else if (type.equalsIgnoreCase("CARD")) {
            return new CardPaymentService();
        }
        throw new IllegalArgumentException("Invalid payment type");
    }
}