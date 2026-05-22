package com.core.capgimini.streams.javafeatures.designattern.abstractFactory;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.Button;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.CheckBox;

public class TestAbstractFactory {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new MacConcretefactories();

        Button button = abstractFactory.createButton();
        button.print();

        CheckBox checkbox = abstractFactory.createCheckbox();
        checkbox.click();
    }
}
