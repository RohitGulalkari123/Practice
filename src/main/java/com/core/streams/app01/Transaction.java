package com.core.streams.app01;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int orderId;
    private double amount;
    private String type; // CREDIT / DEBIT
    private String status; // SUCCESS / FAILED
    private LocalDate date;

    public Transaction(int id, int orderId, double amount, String type, String status, LocalDate date) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Txn{" + id + ", order=" + orderId + ", amt=" + amount + ", " + type + ", " + status + "}";
    }
}
