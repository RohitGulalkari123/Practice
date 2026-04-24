package com.core.designpattern.adptor;

public class ObjectHandlingAdaptor implements ModernInterface{
    private final LegacyClass legacyClass;
    public ObjectHandlingAdaptor(LegacyClass legacyClass) {
        this.legacyClass = legacyClass;
    }

    @Override
    public void getObjects() {
        System.out.println("get Objects from Interfaced class");
    }
}
