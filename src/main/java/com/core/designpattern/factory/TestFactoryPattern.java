package com.core.designpattern.factory;

import com.core.designpattern.factory.inter.PaymentProcessor;

public class TestFactoryPattern {
   public static void main(String args[]) {
        String[] types = {"phonepay", "googlepay"};
        for (String t : types) {
            PaymentProcessor p = PaymentFactory.create(t);
            System.out.println(p.processPayment(100.0));
            System.out.println(p.getReceipt());
    }
}}
