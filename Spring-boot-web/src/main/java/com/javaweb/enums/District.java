package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum District {
    QUAN_1 ("Quận 1"),
    QUAN_2 ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),

    QUAN_TD ("Quận Thủ Đức")
    ;

    private final String districtName;
    District(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static Map<String,String> getDistrict(){
        Map<String,String> district = new LinkedHashMap<>();
        for(District item : District.values()){
            district.put(item.toString() , item.districtName);
        }
        return district;
    }
}
