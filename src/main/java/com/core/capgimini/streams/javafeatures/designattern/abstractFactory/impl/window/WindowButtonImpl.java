package com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.window;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.Button;

public class WindowButtonImpl implements Button {
    @Override
    public void print() {
        System.out.println("Window Button Impl ");
    }
}
