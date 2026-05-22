package com.core.capgimini.streams.javafeatures.designattern.singletone;

public class MongoConnection {
    private volatile static MongoConnection instance;
    MongoConnection(){
        if(instance!=null){
            throw new RuntimeException(
                    "Singleton Broken"
            );
        }
    }

    public static MongoConnection getInstance(){

        if(instance==null){
            System.out.println("Instance Is Null :");
            synchronized (MongoConnection.class){
                if(instance==null){
                    System.out.println("Instance Is Null inside syncronized block :");
                    instance = new MongoConnection();
                }
            }
        }
        System.out.println("Instance Is Not  Null returning the same :");
        return instance;
    }
}
