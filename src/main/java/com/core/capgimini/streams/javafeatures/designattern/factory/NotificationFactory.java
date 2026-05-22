package com.core.capgimini.streams.javafeatures.designattern.factory;

public class NotificationFactory {
    public static Notification createNotification(String type) {
        type=type.toUpperCase();
        switch (type) {
            case "EMAIL":
                return new EmailNotificationImpl();
            case "SMS":
                return new MessageNotificationImpl();
            default:
               throw new RuntimeException("No Implementataions found for Requested Type :" + type);
        }

    }
}
