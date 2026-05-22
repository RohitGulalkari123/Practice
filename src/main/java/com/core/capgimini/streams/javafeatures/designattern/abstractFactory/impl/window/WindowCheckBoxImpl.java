package com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.window;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.CheckBox;

public class WindowCheckBoxImpl implements CheckBox {

    @Override
    public void click() {
        System.out.println("Window CheckBox Impl ");
    }
}
