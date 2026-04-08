package com.core.multithreding.designpatterns.singleton;

class BillPughSingleton {
    /**
     * Bill Pugh Singleton
     * Lazy loaded
     * Thread-safe
     * No synchronization overhead
     **/
    private BillPughSingleton() {
    }

    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }
}