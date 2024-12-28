package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;


@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest searchParams) {
        // TODO Auto-generated method stub
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(searchParams.getName())
                .setDistrict(searchParams.getDistrict())
                .setStreet(searchParams.getStreet())
                .setWard(searchParams.getWard())
                .setNumberOfBasement(searchParams.getNumberOfBasement())
                .setFloorArea(searchParams.getFloorArea())
                .setTypeCode(searchParams.getTypeCode())
                .setManagerName(searchParams.getManagerName())
                .setManagerPhoneNumber(searchParams.getManagerPhone())
                .setAreaFrom(searchParams.getAreaFrom())
                .setAreaTo(searchParams.getAreaTo())
                .setRentPriceFrom(searchParams.getRentPriceFrom())
                .setRentPriceTo(searchParams.getRentPriceTo())
                .setStaffId(searchParams.getStaffId())
                .build();
        return buildingSearchBuilder;


    }
}
