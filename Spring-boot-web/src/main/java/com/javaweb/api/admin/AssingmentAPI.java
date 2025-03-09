package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.impl.AssingmentBuildingService;
import com.javaweb.service.impl.AssingmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assingments")
public class AssingmentAPI {


    @Autowired
    private AssingmentBuildingService assingmentBuildingService;
    // API giao tòa nhà cho nhân viên quản lý

    @Autowired
    private AssingmentCustomerService assingmentCustomerService;

    @PostMapping
        public ResponseEntity<?> updateAssingmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
       // Xuống service
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            assingmentBuildingService.assignBuildingToStaff(assignmentBuildingDTO.getBuildingId(), assignmentBuildingDTO.getStaffIds());
            responseDTO.setMessage("Assign building successfully");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            // Handle specific runtime errors
            responseDTO.setMessage("Assign building failed");
            responseDTO.setDetail(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    // Giao khanh hang cho nhan vien quan ly
    @PostMapping("/customer")
    public ResponseEntity<?> updateAssingmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        // Xuống service
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            assingmentCustomerService.assignCustomerToStaff(assignmentCustomerDTO.getCustomerId(), assignmentCustomerDTO.getStaffIds());
            responseDTO.setMessage("Assign customer successfully");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            // Handle specific runtime errors
            responseDTO.setMessage("Assign customer failed");
            responseDTO.setDetail(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

}
