package com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.mac;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.CheckBox;

public class MacCheckBoxImpl implements CheckBox {

    @Override
    public void click() {
        System.out.println("MacButtonImpl click");
    }
}
