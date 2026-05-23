package com.core.capgimini.streams.javafeatures.corejava.equalshashcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        States st = new States();
        st.setStateId(101);
        st.setStateName("Maharashtra");

        City city = new City();
        city.setCityName("Pune");
        city.setCityPin(511011);
        city.setAreaType("SB Square");

        States s2 = new States();
        s2.setStateId(101);
        s2.setStateName("Maharashtra");

        City city2 = new City();
        city2.setCityName("Pune");
        city2.setCityPin(511011);
        city2.setAreaType("SB Square");

        st.setCities(List.of(city2));
        s2.setCities(List.of(city));

        Set<States> cities = new HashSet<>();
        cities.add(st);
        cities.add(s2);

        cities.stream().forEach(System.out::println);

/**   Before Imp,ementsing the HashCode and Equal mentod
 States{stateId=101, stateName='Maharashtra', cities=[City{cityPin=511011, cityName='Pune', areaType='SB Square'}]}
 States{stateId=101, stateName='Maharashtra', cities=[City{cityPin=511011, cityName='Pune', areaType='SB Square'}]}**/
//After implemenating the hashcode
//States{stateId=101, stateName='Maharashtra', cities=[City{cityPin=511011, cityName='Pune', areaType='SB Square'}]}




    }
}
