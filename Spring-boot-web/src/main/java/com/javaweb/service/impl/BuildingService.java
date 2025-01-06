package com.javaweb.service.impl;


import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
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
import com.javaweb.repository.custom.impl.AssignmentRepositoryImpl;
import com.javaweb.repository.custom.impl.RentAreaRepositoryImpl;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
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

    @Autowired
    private AssignmentRepositoryImpl assignmentRepository;

    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Override
    public List<BuildingResponseDTO> findAll(BuildingSearchRequest searchParams, Pageable pageable) {
        BuildingSearchBuilder builder = buildingSearchBuilder.toBuildingSearchBuilder(searchParams);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageable);
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
    public BuildingDTO findBuildingById(Long id) {

        BuildingEntity entity = buildingRepository.findBuildingById(id);
        return buildingConverter.toBuildingDTO(entity);
    }

//    @Override
//    public BuildingDTO addBuilding(BuildingDTO buildingDTO) {
//        // Chuyển đổi từ DTO sang Entity
//        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
//        // Nếu là cập nhật, xóa các RentArea cũ
//        if (buildingDTO.getId() != null) {
//            BuildingEntity existingEntity = buildingRepository.findById(buildingDTO.getId())
//                    .orElseThrow(() -> new RuntimeException("Building not found"));
//            // Ảnh
//            buildingEntity.setAvatar(existingEntity.getAvatar());
//            // Dùng OneToMany Xóa các RentAreaEntity cũ
//            //rentAreaRepositoryImpl.deleteOneRentAreaByBuildingId(buildingDTO.getId());
//            // Chuyển sang cach 2 dùng cascade
//            existingEntity.getRentAreas().clear(); // Dùng orphanRemoval = true
//        }
//        // Lưu ảnh
//        saveThumbnail(buildingDTO, buildingEntity);
//        // Lưu Entity vào cơ sở dữ liệu
//        BuildingEntity savedEntity = buildingRepository.save(buildingEntity);
//        // Lưu RentAreaEntity nếu có
//        if (buildingEntity.getRentAreas() != null) {
//            for (RentAreaEntity rentArea : buildingEntity.getRentAreas()) {
////                rentAreaRepository.save(rentArea);
//                rentArea.setValue(rentArea.getValue());
//                rentArea.setBuildingEntity(savedEntity);
//                buildingEntity.getRentAreas().add(rentArea);
//            }
//        }
//
//        // Chuyển đổi ngược lại từ Entity sang DTO để trả về
//        return buildingConverter.toBuildingDTO(savedEntity);
//
//    }

    @Override
    public BuildingDTO addBuilding(BuildingDTO buildingDTO) {
        // Initialize the building entity
        BuildingEntity buildingEntity;

        // Check if updating an existing building
        if (buildingDTO.getId() != null) {
            // Retrieve the existing entity
            buildingEntity = buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Building not found"));

            // Retain the avatar
            buildingEntity.setAvatar(buildingEntity.getAvatar());

            // Clear existing RentAreas (orphanRemoval will take care of deletion)
            buildingEntity.getRentAreas().clear();
        } else {
            // Create a new building entity
            buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        }

        // Handle RentAreas (if any) from the DTO
        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            String[] rentAreaValues = buildingDTO.getRentArea().split(",");
            for (String value : rentAreaValues) {
                try {
                    Integer rentAreaValue = Integer.parseInt(value.trim());
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(Long.valueOf(rentAreaValue));
                    rentAreaEntity.setBuildingEntity(buildingEntity); // Link the rent area to the building
                    buildingEntity.getRentAreas().add(rentAreaEntity);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid rent area value: " + value);
                }
            }
        }
        // lưu ảnh
        saveThumbnail(buildingDTO, buildingEntity);

        // Save the entity and return the DTO
        BuildingEntity savedEntity = buildingRepository.save(buildingEntity);

        // Convert to DTO and return
        return buildingConverter.toBuildingDTO(savedEntity);
    }



    @Override
    public void deleteAllByIdIn(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new RuntimeException("Danh sách ID không hợp lệ.");
        }
        // Xóa RentAreaEntity liên quan
//        rentAreaRepositoryImpl.deleteRentAreaByBuildingId(ids); // Xóa các RentArea liên quan
//        assignmentRepository.deleteAssingmentByBuildingId(ids); // Xóa AssignmentBuildingEntity
//        buildingRepository.deleteBuildingById(ids); // Xóa BuildingEntity

        List<BuildingEntity> buildings = buildingRepository.findAllById(ids);

        // Ensure the associations (RentArea) are cleared for each building
        for (BuildingEntity building : buildings) {
            building.getRentAreas().clear(); // This triggers orphanRemoval
        }

        // Delete the buildings (rent areas are deleted due to orphanRemoval)
        buildingRepository.deleteBuildingById(ids);

    }

    @Override
    public List<StaffResponseDTO> getStaffsByBuilding(Long buildingId) {
        // Cách 1: Dùng @ManyToMany

        // Tìm tòa nhà và kiểm tra nếu không tồn tại
//        BuildingEntity building = buildingRepository.findById(buildingId)
//                .orElseThrow(() -> new RuntimeException("Tòa nhà không tồn tại"));
//
//        // Lấy danh sách nhân viên được gán vào tòa nhà
//        // Hoặc qua bên entity dùng fetch.Eager không cần dùng hibernate
//        Hibernate.initialize(building.getAssignedStaffs()); // Tải dữ liệu assignedStaffs
//
//        List<UserEntity> assignedStaffs = building.getAssignedStaffs();
//
//        // Lấy toàn bộ nhân viên có status = 1 và role.code = 'STAFF'
//        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
//
//        // Chuyển đổi sang DTO và kiểm tra nếu nhân viên đã được gán
//        return allStaffs.stream().map(staff -> {
//            StaffResponseDTO dto = new StaffResponseDTO();
//            dto.setStaffId(staff.getId());
//            dto.setFullName(staff.getFullName());
//            dto.setChecked(assignedStaffs.contains(staff) ? "checked" : ""); // Kiểm tra nếu đã gán thì "checked"
//            return dto;
//        }).collect(Collectors.toList());

        // Cách 2: Dùng thủ công OneToMany và ManyToOne


        // Dùng get để lấy dữ liệu
        BuildingEntity building = buildingRepository.findById(buildingId).get();

        // Lấy danh sách AssignmentBuilding của tòa nhà
        List<AssignmentBuildingEntity> assignments = building.getAssignments();

        // Lấy danh sách nhân viên đã được gán vào tòa nhà
        List<Long> assignedStaffIds = assignments.stream()
                .map(assignment -> assignment.getStaff().getId())
                .collect(Collectors.toList());

        // Lấy toàn bộ nhân viên có status = 1 và role.code = 'STAFF'
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        // Chuyển đổi danh sách sang DTO và kiểm tra nếu nhân viên đã được gán
        return allStaffs.stream().map(staff -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            dto.setStaffId(staff.getId());
            dto.setFullName(staff.getFullName());
            dto.setChecked(assignedStaffIds.contains(staff.getId()) ? "checked" : ""); // Kiểm tra nếu đã gán
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public int countTotalBuilding( BuildingSearchRequest params) {
        BuildingSearchBuilder builder = buildingSearchBuilder.toBuildingSearchBuilder(params);
        return buildingRepository.countTotalBuildings(builder);
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
                    File file = new File("C://home/office" + buildingEntity.getAvatar());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }


}



