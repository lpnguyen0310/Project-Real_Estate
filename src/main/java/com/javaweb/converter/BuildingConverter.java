package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingResponseDTO toBuildingResponseDTO(BuildingEntity item) {
        BuildingResponseDTO buildingResponseDTO =  modelMapper.map(item, BuildingResponseDTO.class);
        buildingResponseDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict());
        List<RentAreaEntity> rentAreaEntity = item.getRentAreas();
        String rentArea = rentAreaEntity.stream().map(rent -> rent.getValue().toString()).collect(Collectors.joining(", "));
        buildingResponseDTO.setRentArea(rentArea);
        return buildingResponseDTO;
    }


    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity entity = new BuildingEntity();
        entity.setName(buildingDTO.getName());
        entity.setDistrict(buildingDTO.getDistrict());
        entity.setWard(buildingDTO.getWard());
        entity.setStreet(buildingDTO.getStreet());
        entity.setStructure(buildingDTO.getStructure());
        entity.setNumberOfBasement(buildingDTO.getNumberOfBasement());
        entity.setFloorArea(buildingDTO.getFloorArea());
        entity.setDirection(buildingDTO.getDirection());
        entity.setLevel(buildingDTO.getLevel());
        entity.setRentPrice(buildingDTO.getRentPrice());
        entity.setRentPriceDescription(buildingDTO.getRentPriceDescription());
        entity.setServiceFee(buildingDTO.getServiceFee());
        entity.setCarFee(buildingDTO.getCarFee());
        entity.setMotorbikeFee(buildingDTO.getMotoFee());
        entity.setOvertimeFee(buildingDTO.getOvertimeFee());
        entity.setElectricityFee(buildingDTO.getElectricityFee());
        entity.setWaterFee(buildingDTO.getWaterFee());
        entity.setDeposit(buildingDTO.getDeposit());
        entity.setPayment(buildingDTO.getPayment());
        entity.setRentTime(buildingDTO.getRentTime());
        entity.setDecorationTime(buildingDTO.getDecorationTime());
        entity.setManagerName(buildingDTO.getManagerName());
        entity.setManagerPhoneNumber(buildingDTO.getManagerPhone());
        entity.setTypeCode(String.join(",", buildingDTO.getTypeCode())); // Nối typeCode thành chuỗi
        entity.setNote(buildingDTO.getNote());
        entity.setBrokerageFee(buildingDTO.getBrokerageFee());
        entity.setId(buildingDTO.getId());
        // Xử lý rentArea nếu có
        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            // Tách chuỗi rentArea thành mảng các giá trị rentArea
            List<RentAreaEntity> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                    // Xóa khoảng trắng ở đầu và cuối mỗi chuỗi
                    .map(String::trim)
                    // Chuyển chuỗi thành số nguyên
                    .map(Integer::valueOf)
                    // Chuyển số nguyên lưu vào  RentAreaEntity
                    .map(value -> {
                        RentAreaEntity rentArea = new RentAreaEntity();
                        rentArea.setValue(Long.valueOf(value));
                        rentArea.setBuildingEntity(entity);
                        return rentArea;
                    })
                    .collect(Collectors.toList());
            entity.setRentAreas(rentAreas); // Gán danh sách rentAreas vào BuildingEntity
        }

        return entity;
    }


    // Cái na dunùng để chuyển từ entity sang DTO khi luu vào db
    public BuildingDTO toBuildingDTO(BuildingEntity entity) {
        BuildingDTO dto = new BuildingDTO();
        dto.setName(entity.getName());
        dto.setDistrict(entity.getDistrict());
        dto.setWard(entity.getWard());
        dto.setStreet(entity.getStreet());
        dto.setStructure(entity.getStructure());
        dto.setNumberOfBasement(entity.getNumberOfBasement());
        dto.setFloorArea(entity.getFloorArea());
        dto.setDirection(entity.getDirection());
        dto.setLevel(entity.getLevel());
        dto.setRentPrice(entity.getRentPrice());
        dto.setRentPriceDescription(entity.getRentPriceDescription());
        dto.setServiceFee(entity.getServiceFee());
        dto.setCarFee(entity.getCarFee());
        dto.setMotoFee(entity.getMotorbikeFee());
        dto.setOvertimeFee(entity.getOvertimeFee());
        dto.setElectricityFee(entity.getElectricityFee());
        dto.setWaterFee(entity.getWaterFee());
        dto.setDeposit(entity.getDeposit());
        dto.setPayment(entity.getPayment());
        dto.setRentTime(entity.getRentTime());
        dto.setDecorationTime(entity.getDecorationTime());
        dto.setManagerName(entity.getManagerName());
        dto.setManagerPhone(entity.getManagerPhoneNumber());
        dto.setTypeCode(Arrays.asList(entity.getTypeCode().split(",")));
        dto.setBrokerageFee(entity.getBrokerageFee());
        dto.setId(entity.getId());
        // Xử lý rentArea
        if (entity.getRentAreas() != null && !entity.getRentAreas().isEmpty()) {
            String rentArea = entity.getRentAreas().stream()
                    .map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue()))
                    .collect(Collectors.joining(","));
            dto.setRentArea(rentArea);
        }

        dto.setNote(entity.getNote());
        return dto;
    }
    public void updateBuildingEntityFromDTO(BuildingDTO buildingDTO, BuildingEntity entity) {
        entity.setName(buildingDTO.getName());
        entity.setDistrict(buildingDTO.getDistrict());
        entity.setWard(buildingDTO.getWard());
        entity.setStreet(buildingDTO.getStreet());
        entity.setStructure(buildingDTO.getStructure());
        entity.setNumberOfBasement(buildingDTO.getNumberOfBasement());
        entity.setFloorArea(buildingDTO.getFloorArea());
        entity.setDirection(buildingDTO.getDirection());
        entity.setLevel(buildingDTO.getLevel());
        entity.setRentPrice(buildingDTO.getRentPrice());
        entity.setRentPriceDescription(buildingDTO.getRentPriceDescription());
        entity.setServiceFee(buildingDTO.getServiceFee());
        entity.setCarFee(buildingDTO.getCarFee());
        entity.setMotorbikeFee(buildingDTO.getMotoFee());
        entity.setOvertimeFee(buildingDTO.getOvertimeFee());
        entity.setElectricityFee(buildingDTO.getElectricityFee());
        entity.setWaterFee(buildingDTO.getWaterFee());
        entity.setDeposit(buildingDTO.getDeposit());
        entity.setPayment(buildingDTO.getPayment());
        entity.setRentTime(buildingDTO.getRentTime());
        entity.setDecorationTime(buildingDTO.getDecorationTime());
        entity.setManagerName(buildingDTO.getManagerName());
        entity.setManagerPhoneNumber(buildingDTO.getManagerPhone());
        entity.setTypeCode(String.join(",", buildingDTO.getTypeCode()));
        entity.setNote(buildingDTO.getNote());
        entity.setBrokerageFee(buildingDTO.getBrokerageFee());
        entity.setId(buildingDTO.getId());
        // Xử lý rentArea
        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            // Tách chuỗi rentArea thành mảng các giá trị rentArea
            List<RentAreaEntity> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                    // Xóa khoảng trắng ở đầu và cuối mỗi chuỗi
                    .map(String::trim)
                    // Chuyển chuỗi thành số nguyên
                    .map(Integer::valueOf)
                    // Chuyển số nguyên lưu vào  RentAreaEntity
                    .map(value -> {
                        RentAreaEntity rentArea = new RentAreaEntity();
                        rentArea.setValue(Long.valueOf(value));
                        rentArea.setBuildingEntity(entity);
                        return rentArea;
                    })
                    .collect(Collectors.toList());
            entity.setRentAreas(rentAreas); // Gán danh sách rentAreas vào BuildingEntity
        }
    }
}
