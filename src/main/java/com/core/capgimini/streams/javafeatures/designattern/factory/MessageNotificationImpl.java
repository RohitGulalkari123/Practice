package com.core.capgimini.streams.javafeatures.designattern.factory;

public class MessageNotificationImpl implements Notification {
    @Override
    public void notify(String notType, String message) {
        System.out.println("Message Notification Impl: " + message);
    }
}
