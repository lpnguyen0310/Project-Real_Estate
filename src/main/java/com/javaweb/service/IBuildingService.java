package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface IBuildingService {
    List<BuildingResponseDTO> findAll(BuildingSearchRequest searchParams);
    BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuildingById(List<Long> ids);

    BuildingDTO findBuildingById(Long id);
    // Lưu building

    BuildingDTO addBuilding(BuildingDTO buildingDTO);

    // Xóa building
    void deleteBuilding(Long id);

    // Xóa List Tòa nhà theo id
    void deleteAllByIdIn(List<Long> ids);

    List<StaffResponseDTO> getStaffsByBuilding(Long buildingId);


}
