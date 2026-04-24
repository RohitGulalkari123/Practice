package com.core.designpattern.singletone;

public class MongoInstance {
    private static volatile MongoInstance mongoInstance;

    public static MongoInstance getMongoInstance() {
        if (mongoInstance == null) {
            synchronized (MongoInstance.class) {
                if (mongoInstance == null) {
                    mongoInstance = new MongoInstance();
                    System.out.println("Created New Instance :");
                }
            }
        } else {
            System.out.println("Using Old Instance :");

        }

        return mongoInstance;
    }
}
