package com.core.multithreding.designpatterns.stratagy;

class PremiumDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount * 0.8;
    }
}