package com.core.capgimini.streams.javafeatures.designattern.abstractFactory;

import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.Button;
import com.core.capgimini.streams.javafeatures.designattern.abstractFactory.inter.CheckBox;

import java.awt.*;

public interface AbstractFactory {
    Button createButton();
    CheckBox createCheckbox();
}
