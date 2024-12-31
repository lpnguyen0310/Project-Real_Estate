package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuilder builder);

    BuildingEntity findBuildingById(Long id);

    // Xóa List Tòa nhà theo id
    void deleteAllByIdIn(List<Long> ids);
    void deleteBuildingById(Long id);
}
