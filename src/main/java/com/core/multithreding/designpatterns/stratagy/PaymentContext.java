package com.core.multithreding.designpatterns.stratagy;

class PaymentContext {

    private DiscountStrategy strategy;

    public PaymentContext(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double process(double amount) {
        return strategy.applyDiscount(amount);
    }
}