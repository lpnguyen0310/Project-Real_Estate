package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {

    // Tìm tất cả khách hàng
    List<CustomerEntity> findAllCustomer(CustomerSearchBuilder builder, Pageable pageable);

    int countTotalBuildings( CustomerSearchBuilder builder);


    // Delete customer
   void deleteCustomer(List<Long> ids);

   CustomerEntity findCustomerById(Long id);

   // Tìm 1 khách hàng theo số điện thoại và trạng thái
    CustomerEntity findOneCustomerByPhoneAndIs_active(String phone, int is_active);

}
