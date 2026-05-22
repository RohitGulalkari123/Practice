package com.core.capgimini.streams.javafeatures.designattern.abstractFactory;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.mac.MacButtonImpl;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.impl.mac.MacCheckBoxImpl;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.Button;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.CheckBox;

public class MacConcretefactories implements AbstractFactory{
    @Override
    public Button createButton() {
        return new MacButtonImpl();
    }

    @Override
    public CheckBox createCheckbox() {
        return new MacCheckBoxImpl();
    }
}
