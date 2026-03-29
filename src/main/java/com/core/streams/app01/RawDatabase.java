package com.core.streams.app01;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class RawDatabase {

    public static List<Order> getOrders() {
        return Arrays.asList(
                new Order(1, "Amit", "DELIVERED", "ELECTRONICS", 12000),
                new Order(2, "Rohit", "SHIPPED", "GROCERY", 2000),
                new Order(3, "Amit", "DELIVERED", "ELECTRONICS", 15000),
                new Order(4, "Neha", "NEW", "GROCERY", 3000),
                new Order(5, "Rohit", "DELIVERED", "ELECTRONICS", 22000)
        );
    }

    public static List<Transaction> getTransactions() {
        return Arrays.asList(
                new Transaction(1, 1, 12000, "CREDIT", "SUCCESS", LocalDate.now()),
                new Transaction(2, 2, 2000, "DEBIT", "FAILED", LocalDate.now()),
                new Transaction(3, 3, 15000, "CREDIT", "SUCCESS", LocalDate.now()),
                new Transaction(4, 4, 3000, "DEBIT", "SUCCESS", LocalDate.now()),
                new Transaction(5, 5, 22000, "CREDIT", "SUCCESS", LocalDate.now()),
                new Transaction(6, 1, 12000, "CREDIT", "SUCCESS", LocalDate.now()) // duplicate
        );
    }
}

