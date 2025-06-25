package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    List<BuildingResponseDTO> findAll(BuildingSearchRequest searchParams, Pageable pageable);
    BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO);


    BuildingDTO findBuildingById(Long id);
    // Lưu building

    BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);

    // Xóa building


    // Xóa List Tòa nhà theo id
    void deleteAllByIdIn(List<Long> ids);

    List<StaffResponseDTO> getStaffsByBuilding(Long buildingId);

    int countTotalBuilding(BuildingSearchRequest params);

    boolean findBuildingByIdAndStaffId(Long buildingId, Long staffId);


    // web
    List<BuildingResponseDTO> findFeaturedBuildings();
    List<BuildingResponseDTO> findLatestBuildings();
    List<BuildingResponseDTO> findMostViewedBuildings();

}
