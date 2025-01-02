package com.javaweb.repository.custom;

import java.util.List;

public interface AssignmentRepositoryCustom {
    boolean deleteAssingmentByBuildingId(List<Long> buildingId);
}
