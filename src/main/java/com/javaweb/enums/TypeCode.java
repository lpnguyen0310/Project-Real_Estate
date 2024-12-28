package com.javaweb.enums;


import java.util.*;

public enum TypeCode {
    TANG_TRET ("Tầng Trệt "),
    NGUYEN_CAN ("Nguyên Căn "),
    NOI_THAT ("Nội Thất ");

    private final String typeName;

    TypeCode(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new LinkedHashMap<>();
        for(TypeCode item : TypeCode.values()){
            listType.put(item.toString() , item.typeName);
        }
        return listType;
    }
}
