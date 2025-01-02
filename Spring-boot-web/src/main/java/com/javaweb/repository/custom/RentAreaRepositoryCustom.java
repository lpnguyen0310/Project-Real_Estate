package com.javaweb.repository.custom;

import java.util.List;

public interface RentAreaRepositoryCustom {

    // Xóa rent area
    boolean deleteRentAreaByBuildingId(List<Long> buildingId);

    boolean deleteOneRentAreaByBuildingId(Long buildingId);
}
