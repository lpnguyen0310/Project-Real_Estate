package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerResponseDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustormerService {
    CustomerDTO save(CustomerDTO customerDTO);

    // Tìm tất cả khách hàng
    List<CustomerResponseDTO> findAllCustomer(CustomerSearchRequest searchParams, Pageable pageable);

    int countTotalBuilding(CustomerSearchRequest params);


    // Delete customer
    void deleteListCustomer(List<Long> ids);

}
