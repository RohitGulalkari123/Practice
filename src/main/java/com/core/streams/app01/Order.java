package com.core.streams.app01;

import java.time.LocalDate;

public class Order {
    private int id;
    private String customer;
    private String status; // NEW / SHIPPED / DELIVERED
    private String category; // ELECTRONICS / GROCERY
    private double totalAmount;

    public Order(int id, String customer, String status, String category, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.category = category;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" + id + ", " + customer + ", " + totalAmount + "}";
    }
}
