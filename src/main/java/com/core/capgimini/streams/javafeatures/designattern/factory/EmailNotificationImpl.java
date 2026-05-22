package com.core.capgimini.streams.javafeatures.designattern.factory;

public class EmailNotificationImpl implements Notification{
    @Override
    public void notify(String notType, String message) {
        System.out.println("Email Notification Notification "+message);
    }
}
