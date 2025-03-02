package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingResponseDTO toBuildingResponseDTO(BuildingEntity item) {
        BuildingResponseDTO buildingResponseDTO =  modelMapper.map(item, BuildingResponseDTO.class);
        // Dùng Stream
//        String districtName = Arrays.stream(District.values())
//                .filter(district -> district.name().equals(item.getDistrict()))
//                .map(District::getDistrictName)
//                .findFirst()
//                .orElse(item.getDistrict());

        // Dùng Map dễ hiểu
        Map<String,String> districtMap = District.getDistrict();
        String districtName = districtMap.getOrDefault(item.getDistrict(), item.getDistrict());

        buildingResponseDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtName);
        List<RentAreaEntity> rentAreaEntity = item.getRentAreas();
        String rentArea = rentAreaEntity.stream().map(rent -> rent.getValue().toString()).collect(Collectors.joining(", "));
        buildingResponseDTO.setRentArea(rentArea);
        return buildingResponseDTO;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity entity = modelMapper.map(buildingDTO, BuildingEntity.class);

        // Xử lý typeCode (chuyển List<String> thành chuỗi nối bằng dấu ",")
        if (buildingDTO.getTypeCode() != null && !buildingDTO.getTypeCode().isEmpty()) {
            entity.setTypeCode(String.join(",", buildingDTO.getTypeCode()));
        }

        // Xử lý rentArea (chuyển chuỗi thành danh sách RentAreaEntity)
        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            List<RentAreaEntity> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .map(value -> {
                        RentAreaEntity rentArea = new RentAreaEntity();
                        rentArea.setValue(Long.valueOf(value));
                        rentArea.setBuildingEntity(entity);
                        return rentArea;
                    })
                    .collect(Collectors.toList());
            entity.setRentAreas(rentAreas);
        }

        return entity;
    }


    public BuildingDTO toBuildingDTO(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);

        // Xử lý typeCode (chuyển chuỗi thành danh sách)
        if (entity.getTypeCode() != null && !entity.getTypeCode().isEmpty()) {
            dto.setTypeCode(Arrays.asList(entity.getTypeCode().split(",")));
        }

        // Xử lý rentArea (chuyển danh sách RentAreaEntity thành chuỗi)
        if (entity.getRentAreas() != null && !entity.getRentAreas().isEmpty()) {
            String rentArea = entity.getRentAreas().stream()
                    .map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue()))
                    .collect(Collectors.joining(","));
            dto.setRentArea(rentArea);
        }

        return dto;
    }

}
