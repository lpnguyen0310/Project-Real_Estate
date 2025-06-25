package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>, BuildingRepositoryCustom {
//    void deleteRentAreaByBuildingId(Long id);
    //BuildingEntity findOneByBuildingId(Long buildingId);

//    void deleteBuildingEntitiesBy(Long id);
// Web cho khah haàng
// Bất động sản nổi bật
    List<BuildingEntity> findByIsFeaturedTrue(Pageable pageable);

    // Bất động sản mới nhất
    List<BuildingEntity> findAllByOrderByCreatedDateDesc(Pageable pageable);

    // Bất động sản xem nhiều
    List<BuildingEntity> findAllByOrderByViewCountDesc(Pageable pageable);
}
