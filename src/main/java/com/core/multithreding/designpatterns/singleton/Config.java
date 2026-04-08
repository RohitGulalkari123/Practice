package com.core.multithreding.designpatterns.singleton;

public class Config {

    //Double Checked Locking (BEST PRACTICAL)
    private Config() {
        //to avoid same instance due to reflectiojn
        if (config != null) {
            //throw new RuntimeException("Use getInstance()");
            System.out.println("Duplicate config found!");
        }
    }

    /**
     * volatile : Prevents instruction reordering
     * Ensures visibility across threads
     **/
    private static volatile Config config;

    public static Config getInstance() {

        if (config == null) {
            synchronized (Config.class) {
                if (config == null) {
                    config = new Config();
                }

            }
        }
        return config;
    }



}
