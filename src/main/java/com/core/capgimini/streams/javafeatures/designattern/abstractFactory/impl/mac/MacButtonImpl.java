package com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.mac;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.Button;

public class MacButtonImpl implements Button {
    @Override
    public void print() {
        System.out.println("MacButtonImpl");
    }
}
