package com.core.capgimini.streams.javafeatures.designattern.abstractFactory;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.window.WindowButtonImpl;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.window.WindowCheckBoxImpl;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.Button;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.CheckBox;

public class WindowConcretefactories implements AbstractFactory {
    @Override
    public Button createButton() {
        return new WindowButtonImpl();
    }

    @Override
    public CheckBox createCheckbox() {
        return new WindowCheckBoxImpl();
    }
}
