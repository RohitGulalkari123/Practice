package com.core.capgimini.streams.javafeatures.corejava.equalshashcode;

import java.util.Objects;

public class City {
    private int  cityPin;
    private String cityName;
    private String areaType;

    public City() {}
    public City(int cityPin, String cityName, String areaType) {
        this.cityPin = cityPin;
        this.cityName = cityName;
        this.areaType = areaType;
    }

    public void setCityPin(int cityPin) {
        this.cityPin = cityPin;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public int getCityPin() {
        return cityPin;
    }

    public String getCityName() {
        return cityName;
    }

    public String getAreaType() {
        return areaType;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityPin=" + cityPin +
                ", cityName='" + cityName + '\'' +
                ", areaType='" + areaType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityPin == city.cityPin && Objects.equals(cityName, city.cityName) && Objects.equals(areaType, city.areaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityPin, cityName, areaType);
    }
}
