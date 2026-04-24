package com.core.designpattern.prototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameCharacter implements Cloneable {
    private String name;
    private int hp, mp;
    private List<String> skills;
    private Map<String,String> equipment;

    public GameCharacter(List<String> skills, String name, int hp, int mp, Map<String, String> equipment) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.skills = new ArrayList<>(skills);//deep copy ->we are copying everything of the objecrts
        this.equipment = new HashMap<>(equipment); // deep copy
    }

    @Override
    public GameCharacter clone() {
        try {
            GameCharacter clone = (GameCharacter) super.clone();
            clone.skills = new ArrayList<>(skills);//deep copy ->we are copying everything of the objecrts
            clone.equipment = new HashMap<>(equipment);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
