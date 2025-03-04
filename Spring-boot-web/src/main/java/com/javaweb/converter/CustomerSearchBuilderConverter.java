package com.javaweb.converter;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.model.request.CustomerSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerSearchBuilderConverter {

    public CustomerSearchBuilder toCustomerSearchBuilder(CustomerSearchRequest searchParams) {
        // TODO Auto-generated method stub
        CustomerSearchBuilder customerSearchBuilder = new CustomerSearchBuilder.Builder()
                .setFullName(searchParams.getFullName())
                .setPhone(searchParams.getPhone())
                .setEmail(searchParams.getEmail())
                .setStatus(searchParams.getStatus())
                .build();
        return customerSearchBuilder;
    }

}
