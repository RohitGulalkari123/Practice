package com.core.designpattern.builder;

import com.core.designpattern.factory.PaymentFactory;
import com.core.designpattern.factory.inter.PaymentProcessor;

public class TestBuilder {
    public static void main(String args[]) {
        User user = new User.Builder("Ravi", "ravi@example.com")
                .age(28)
                .phone("+91-9876543210")
                .address("Wardha, Maharashtra")
                .build();

        System.out.println("user :{}"+user);
    }
}
