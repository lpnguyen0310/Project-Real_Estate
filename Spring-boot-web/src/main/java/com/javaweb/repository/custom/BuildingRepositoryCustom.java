package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable);

    BuildingEntity findBuildingById(Long id);

    // Xóa List Tòa nhà theo id
    void deleteBuildingById(List<Long> id);

    int countTotalBuildings( BuildingSearchBuilder builder);
}
