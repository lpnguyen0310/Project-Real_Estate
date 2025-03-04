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
        // Chuyener Enum status sang String
//        Map<String,String> statusMap = Status.getStatus();
//        String statusName = statusMap.get(entity.getStatus());
//        customerDTO.setStatus(statusName);
        if(entity.getStatus() != null){
            Map<String,String> statusMap = Status.getStatus();
            String statusName = statusMap.get(entity.getStatus());
            customerDTO.setStatus(statusName);
        }
        return customerDTO;
    }

    // Chuyển từ Entity sang DTO
    public CustomerEntity convertToEntity(CustomerDTO dto) {
        CustomerEntity customerEntity = modelMapper.map(dto, CustomerEntity.class);
        // Chuyener Enum status sang String
        if (dto.getStatus() != null) {
            for (Status s : Status.values()) {
                if (s.getStatusName().equals(dto.getStatus())) {
                    customerEntity.setStatus(s.getStatusName()); // Lưu "Chưa xử lý"
                    break;
                }
            }
        } else {
            customerEntity.setStatus(Status.CHUA_XU_LY.getStatusName()); // Mặc định "Chưa xử lý"
        }
        return customerEntity;
    }


    public CustomerResponseDTO toCustomerResponseDTO(CustomerEntity item) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(item, CustomerResponseDTO.class);
        // In ra giá trị thực tế của status từ Entity
        System.out.println("===== Debugging Status Mapping =====");
        System.out.println("Entity Status (Enum): " + item.getStatus());
//        if(item.getStatus() != null){
//            Map<String,String> statusMap = Status.getStatus();
//            String statusName = statusMap.get(item.getStatus());
//            customerResponseDTO.setStatus(statusName);
//
//        }
        return customerResponseDTO;
    }

}
