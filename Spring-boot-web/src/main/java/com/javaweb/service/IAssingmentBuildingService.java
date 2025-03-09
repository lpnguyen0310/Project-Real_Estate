package com.javaweb.service;

import java.util.List;

public interface IAssingmentBuildingService {
    void assignBuildingToStaff(Long buildingId, List<Long> staffIds);


}
