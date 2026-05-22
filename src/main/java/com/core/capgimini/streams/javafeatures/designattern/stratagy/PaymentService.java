package com.core.capgimini.streams.javafeatures.designattern.stratagy;

public class PaymentService {
    PaymentStratagy paymentStratagy;
    public PaymentService(PaymentStratagy paymentStratagy){
        this.paymentStratagy = paymentStratagy;
    }

    public void processPayment(int amount){
        paymentStratagy.pay(amount);
    }
}
