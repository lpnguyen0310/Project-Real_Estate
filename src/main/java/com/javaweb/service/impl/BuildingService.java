package com.javaweb.service.impl;


import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.impl.RentAreaRepositoryImpl;
import com.javaweb.service.IBuildingService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private RentAreaRepositoryImpl rentAreaRepositoryImpl;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingConverter buildingConverter;
    @Override
    public List<BuildingResponseDTO> findAll(BuildingSearchRequest searchParams) {
        BuildingSearchBuilder builder = buildingSearchBuilder.toBuildingSearchBuilder(searchParams);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder);
        List<BuildingResponseDTO> results = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingResponseDTO buildingResponseDTO = buildingConverter.toBuildingResponseDTO(item);
            results.add(buildingResponseDTO);
        }
        return results;
    }

    @Override
    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) {
        return null;
    }

    @Override
    public void deleteBuildingById(List<Long> ids) {

    }

    @Override
    public BuildingDTO findBuildingById(Long id) {

        BuildingEntity entity = buildingRepository.findBuildingById(id);
        return buildingConverter.toBuildingDTO(entity);
    }

    @Override
    public BuildingDTO addBuilding(BuildingDTO buildingDTO) {
        // Chuyển đổi từ DTO sang Entity
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        // Nếu là cập nhật, xóa các RentArea cũ
        if (buildingDTO.getId() != null) {
            BuildingEntity existingEntity = buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Building not found"));

            // Xóa các RentAreaEntity cũ
            rentAreaRepositoryImpl.deleteRentAreaByBuildingId(buildingDTO.getId());
        }

        // Lưu Entity vào cơ sở dữ liệu
        BuildingEntity savedEntity = buildingRepository.save(buildingEntity);
        // Lưu RentAreaEntity nếu có
        if (buildingEntity.getRentAreas() != null) {
            for (RentAreaEntity rentArea : buildingEntity.getRentAreas()) {
                rentAreaRepository.save(rentArea);
            }
        }

        // Chuyển đổi ngược lại từ Entity sang DTO để trả về
        return buildingConverter.toBuildingDTO(savedEntity);

    }

    @Override
    public void deleteBuilding(Long id) {

    }

    @Override
    public void deleteAllByIdIn(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new RuntimeException("Danh sách ID không hợp lệ.");
        }
        // Xóa RentAreaEntity liên quan
        for (Long id : ids) {
            rentAreaRepositoryImpl.deleteRentAreaByBuildingId(id); // Xóa các RentArea liên quan
            buildingRepository.deleteById(id); // Xóa BuildingEntity
        }

    }

    @Override
    public List<StaffResponseDTO> getStaffsByBuilding(Long buildingId) {
        // Tìm tòa nhà và kiểm tra nếu không tồn tại
        BuildingEntity building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Tòa nhà không tồn tại"));

        // Lấy danh sách nhân viên được gán vào tòa nhà
        Hibernate.initialize(building.getAssignedStaffs()); // Tải dữ liệu assignedStaffs
        List<UserEntity> assignedStaffs = building.getAssignedStaffs();

        // Lấy toàn bộ nhân viên có status = 1 và role.code = 'STAFF'
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        // Chuyển đổi sang DTO và kiểm tra nếu nhân viên đã được gán
        return allStaffs.stream().map(staff -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            dto.setStaffId(staff.getId());
            dto.setFullName(staff.getFullName());
            dto.setChecked(assignedStaffs.contains(staff) ? "checked" : ""); // Kiểm tra nếu đã gán thì "checked"
            return dto;
        }).collect(Collectors.toList());
    }

}



