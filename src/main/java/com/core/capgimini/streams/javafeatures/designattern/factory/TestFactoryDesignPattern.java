package com.core.capgimini.streams.javafeatures.designattern.factory;

public class TestFactoryDesignPattern {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.createNotification("EMAIL");
        notification.notify("Greetings !!! ","Wel-Come Onbaords");
    }
}
