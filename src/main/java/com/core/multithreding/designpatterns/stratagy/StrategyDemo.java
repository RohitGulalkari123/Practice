package com.core.multithreding.designpatterns.stratagy;

public class StrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext(new FestivalDiscount());
        System.out.println(context.process(1000));
    }
}
