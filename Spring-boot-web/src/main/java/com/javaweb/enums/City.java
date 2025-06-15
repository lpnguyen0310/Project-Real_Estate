package com.javaweb.enums;

import com.javaweb.utils.DistrictCode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public enum City {

    TP_HCM ("Quận 1"),
    TP_HANOI ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),

    QUAN_TD ("Quận Thủ Đức")
    ;

    private final String cityName;
    City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new TreeMap<>();
        for(City item : City.values()){
            listType.put(item.toString() , item.cityName);
        }
        return listType;
    }
}
