package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuilder builder);

    BuildingEntity findBuildingById(Long id);

    // Xóa List Tòa nhà theo id
    void deleteBuildingById(List<Long> id);
}
