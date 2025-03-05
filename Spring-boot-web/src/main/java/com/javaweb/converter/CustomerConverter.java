package com.javaweb.converter;


import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;


    // Chuyển từ DTO sang Entity
    public CustomerDTO convertToDto(CustomerEntity entity) {
        CustomerDTO customerDTO = modelMapper.map(entity, CustomerDTO.class);

        if (entity.getStatus() != null) {
            customerDTO.setStatus(entity.getStatus());  // Trả về nguyên trạng từ database
        }
        return customerDTO;
    }

    // Chuyển từ Entity sang DTO
    public CustomerEntity convertToEntity(CustomerDTO dto) {
        CustomerEntity customerEntity = modelMapper.map(dto, CustomerEntity.class);
        // Chuyener Enum status sang String
        if (dto.getStatus() != null) {
            for (Status item : Status.values()) {
                if (item.getStatusName().equals(dto.getStatus())) {
                    customerEntity.setStatus(item.getStatusName()); // Lưu Enum dưới dạng CHUA_XU_LY
                    break;
                }
            }
        }
        return customerEntity;
    }


    public CustomerResponseDTO toCustomerResponseDTO(CustomerEntity item) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(item, CustomerResponseDTO.class);
        if(item.getStatus() != null){
            Map<String,String> statusMap = Status.getStatus();
            String statusName = statusMap.get(item.getStatus()); // Lấy tên đầy đủ của Enum
            customerResponseDTO.setStatus(statusName);
        }


        return customerResponseDTO;
    }

}
