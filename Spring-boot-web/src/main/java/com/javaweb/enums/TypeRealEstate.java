package com.javaweb.enums;

import java.util.Map;

public enum TypeRealEstate {

    CAN_HO ("Căn hộ"),
    NHA_PHO ("Nhà phố"),

    NHA_RIENG ("Nhà riêng"),

    NHA_PHO_THUONG_MAI ("Nhà phố thương mại"),

    NHA_BIET_THU ("Nhà biệt thự"),

    NHA_TRO ("Nhà trọ"),

    VILLA ("Biệt thự"),

    DAT_NEN ("Đất nền"),

    DAT_NONG_NGHIEP ("Đất nông nghiệp"),

    DAT_O_THON ("Đất ở thôn"),
    ;

    private final String typeNameEstate;

    TypeRealEstate(String typeNameEstate) {
        this.typeNameEstate = typeNameEstate;
    }

    public String getTypeNameEstate() {
        return typeNameEstate;
    }

    public static Map<String,String> typeEstate() {
        Map<String,String> listType = new java.util.TreeMap<>();
        for(TypeRealEstate item : TypeRealEstate.values()){
            listType.put(item.toString(), item.typeNameEstate);
        }
        return listType;
    }
}
