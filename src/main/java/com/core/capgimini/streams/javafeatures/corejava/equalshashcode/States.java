package com.core.capgimini.streams.javafeatures.corejava.equalshashcode;

import java.util.List;
import java.util.Objects;

public class States {
    private int stateId;
    private String stateName;
    private List<City> cities;

    public States() {}

    public States(int stateId, String stateName, List<City> cities) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.cities = cities;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "States{" +
                "stateId=" + stateId +
                ", stateName='" + stateName + '\'' +
                ", cities=" + cities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        States states = (States) o;
        return stateId == states.stateId && Objects.equals(stateName, states.stateName) && Objects.equals(cities, states.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId, stateName, cities);
    }
}
