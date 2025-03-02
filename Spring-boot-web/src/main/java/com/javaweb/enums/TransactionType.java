package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionType {

    CSKH("Chăm sóc khách hàng"),
    DDX ("Dẫn đi xem nhà");

    private final String transactionTypeName;


    TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }


    public static Map<String,String> transactionType(){
        Map<String,String> listTransactionType = new LinkedHashMap<>();
        for(TransactionType item: TransactionType.values()){
            listTransactionType.put(item.toString(), item.transactionTypeName); // item.toString() return the name of the enum
        }
        return listTransactionType;
    }
}
