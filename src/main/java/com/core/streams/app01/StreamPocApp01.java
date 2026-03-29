package com.core.streams.app01;

import java.util.List;

public class StreamPocApp01 {
    public static void main(String[] args) {
        List<Order> orders = RawDatabase.getOrders();
        List<Transaction> txns = RawDatabase.getTransactions();



    }
}
