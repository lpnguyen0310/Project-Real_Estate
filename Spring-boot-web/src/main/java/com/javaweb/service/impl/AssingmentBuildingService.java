package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AssignmentBuildingRepository;
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

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Override
    public void assignBuildingToStaff(Long buildingId, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        // Lấy danh sách nhân viên từ danh sách staffIds
        // Lấy danh sách các bản ghi hiện tại trong bảng assignment_building
        List<AssignmentBuildingEntity> existingAssignments = assignmentBuildingRepository.findByBuilding(buildingEntity);


        // Xóa các bản ghi cũ không nằm trong danh sách mới
        for (AssignmentBuildingEntity assignment : existingAssignments) {
            if (!staffIds.contains(assignment.getStaff().getId())) {
                assignmentBuildingRepository.delete(assignment);
            }
        }

//        // Lưu thay đổi
//        buildingRepository.save(buildingEntity);
        // Tạo mới các bản ghi cho nhân viên chưa được gán
        for (Long staffId : staffIds) {
            if (existingAssignments.stream().noneMatch(a -> a.getStaff().getId().equals(staffId))) {
                UserEntity staff = userRepository.findById(staffId)
                        .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
                AssignmentBuildingEntity newAssignment = new AssignmentBuildingEntity();
                newAssignment.setBuilding(buildingEntity);
                newAssignment.setStaff(staff);
                assignmentBuildingRepository.save(newAssignment);
            }
        }

    }
}
