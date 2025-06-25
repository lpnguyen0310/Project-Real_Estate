package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAsignmentCustomerService;
import com.javaweb.service.IAssingmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssingmentCustomerService implements IAsignmentCustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void assignCustomerToStaff(Long CustomerId, List<Long> staffIds) {
        CustomerEntity customerEntity = customerRepository.findById(CustomerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Long> existingStaffIds = customerEntity.getUserEntities().stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());

        // Lọc ra các nhân viên cần thêm và cần xóa
        List<UserEntity> staffToAdd = userRepository.findAllById(
                staffIds.stream()
                        .filter(id -> !existingStaffIds.contains(id))
                        .collect(Collectors.toList())
        );

        List<UserEntity> staffToRemove = customerEntity.getUserEntities().stream()
                .filter(user -> !staffIds.contains(user.getId()))
                .collect(Collectors.toList());
        // Xóa nhân viên không còn trong danh sách mới
        customerEntity.getUserEntities().removeAll(staffToRemove);

        // Thêm nhân viên mới
        customerEntity.getUserEntities().addAll(staffToAdd);

        // Lưu tòa nhà
        customerRepository.save(customerEntity);
    }
}
