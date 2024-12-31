package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>, BuildingRepositoryCustom {
//    void deleteRentAreaByBuildingId(Long id);
    //BuildingEntity findOneByBuildingId(Long buildingId);

//    void deleteBuildingEntitiesBy(Long id);
}
