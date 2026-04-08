package com.core.multithreding.designpatterns.stratagy;

class FestivalDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount * 0.9;
    }
}