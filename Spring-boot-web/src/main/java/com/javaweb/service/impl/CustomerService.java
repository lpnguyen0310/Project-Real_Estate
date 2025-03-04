package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerResponseDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ICustormerService;
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



    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        if (customerEntity.getStatus() == null) {
            customerEntity.setStatus(String.valueOf(Status.CHUA_XU_LY));
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
}
