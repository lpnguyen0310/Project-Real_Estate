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

@Service
@Transactional
public class AssingmentBuildingService implements IAssingmentBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void assignBuildingToStaff(Long buildingId, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        // Lấy danh sách nhân viên từ danh sách staffIds
        List<UserEntity> staffs = userRepository.findAllById(staffIds);


        // Gán nhân viên cho tòa nhà
        buildingEntity.setAssignedStaffs(staffs);

        // Lưu thay đổi
        buildingRepository.save(buildingEntity);

    }
}
