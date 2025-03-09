package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.Status;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerResponseDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.service.ICustormerService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService implements ICustormerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerSearchBuilderConverter customerSearchBuilderConverter;


    @Autowired
    private UserRepository userRepository;


    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        if (customerEntity.getStatus() == null) {
            customerEntity.setStatus(Status.CHUA_XU_LY.getStatusName());
        }
        customerEntity = customerRepository.save(customerEntity);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public List<CustomerResponseDTO> findAllCustomer(CustomerSearchRequest searchParams, Pageable pageable) {
        CustomerSearchBuilder builder = customerSearchBuilderConverter.toCustomerSearchBuilder(searchParams);
        List<CustomerEntity> customerEntities = customerRepository.findAllCustomer(builder, pageable);
        List<CustomerResponseDTO> results = new ArrayList<>();
        for (CustomerEntity item : customerEntities) {
            CustomerResponseDTO customerResponseDTO = customerConverter.toCustomerResponseDTO(item);
            results.add(customerResponseDTO);
        }
        return results;
    }

    @Override
    public int countTotalBuilding(CustomerSearchRequest params) {
        CustomerSearchBuilder builder = customerSearchBuilderConverter.toCustomerSearchBuilder(params);
        return customerRepository.countTotalBuildings(builder);
    }

    @Override
    public void deleteListCustomer(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            throw new RuntimeException("List id is empty");
        }

        List<CustomerEntity> customerEntities = customerRepository.findAllById(ids);
        // Xu l khi noi ban với nhau
        customerRepository.deleteCustomer(ids);
    }

    @Override
    public CustomerDTO createOrUpdateCustomer(CustomerDTO customerDTO) throws MyException {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        // Kiêểm tra phone tồn tại chưa và is_active = 1
        CustomerEntity existCustomer = customerRepository.findOneCustomerByPhone(customerDTO.getPhone());
        if (existCustomer != null && (customerDTO.getId() == null || !customerDTO.getId().equals(existCustomer.getId()))) {
            throw new MyException("Số điện thoại đã tồn tại trong hệ thống");
        }
//        if(customerRepository.findOneCustomerByPhone(customerDTO.getPhone()) != null){
//            throw new MyException("Số điện thoại đã tồn tại");
//
//        }
        if (customerEntity.getId() != null) {
            CustomerEntity oldCustomer = customerRepository.findCustomerById(customerDTO.getId());
            if (oldCustomer != null) {
                customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
                customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
            }
        }
        customerEntity = customerRepository.save(customerEntity);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        CustomerEntity entity = customerRepository.findCustomerById(id);
        return customerConverter.convertToDto(entity);
    }

    @Override
    public List<StaffResponseDTO> getStaffsByCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findCustomerById(customerId);
        Hibernate.initialize(customerEntity.getUserEntities());
        List<UserEntity> userEntities = customerEntity.getUserEntities();
        // Lay ra danh sach nhan vien co role la staff va status = 1
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        return allStaffs.stream().map(staff -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            dto.setStaffId(staff.getId());
            dto.setFullName(staff.getFullName());
            dto.setChecked(userEntities.contains(staff) ? "checked" : "");
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponseDTO> findCustomerByStaff(Long staffId, CustomerSearchRequest params, Pageable pageable) {
        return null;
    }


}
