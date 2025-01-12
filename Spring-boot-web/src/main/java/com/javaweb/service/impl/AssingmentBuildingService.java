package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssingmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssingmentBuildingService implements IAssingmentBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void assignBuildingToStaff(Long buildingId, List<Long> staffIds) {
        // Lấy thực thể tòa nhà
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        // Lấy danh sách ID của nhân viên hiện tại
        List<Long> existingStaffIds = buildingEntity.getAssignedStaffs().stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());

        // Lọc ra các nhân viên cần thêm và cần xóa
        List<UserEntity> staffToAdd = userRepository.findAllById(
                staffIds.stream()
                        .filter(id -> !existingStaffIds.contains(id))
                        .collect(Collectors.toList())
        );

        List<UserEntity> staffToRemove = buildingEntity.getAssignedStaffs().stream()
                .filter(user -> !staffIds.contains(user.getId()))
                .collect(Collectors.toList());

        // Xóa nhân viên không còn trong danh sách mới
        buildingEntity.getAssignedStaffs().removeAll(staffToRemove);

        // Thêm nhân viên mới
        buildingEntity.getAssignedStaffs().addAll(staffToAdd);

        // Lưu tòa nhà
        buildingRepository.save(buildingEntity);
    }
}
