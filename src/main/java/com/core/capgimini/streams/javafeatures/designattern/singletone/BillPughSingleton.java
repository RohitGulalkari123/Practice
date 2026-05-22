package com.core.capgimini.streams.javafeatures.designattern.singletone;

public class BillPughSingleton {
    private BillPughSingleton() {
    }

    private static class Holder {
        static final MongoConnection INSTANCE = new MongoConnection();
    }

    public static MongoConnection getInstance() {
        return Holder.INSTANCE;
    }
}
