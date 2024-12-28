package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.impl.AssingmentBuildingService;
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
    @PostMapping
        public ResponseEntity<?> updateAssingmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
       // Xuống service
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            // Call the service to assign staff to the building
            assingmentBuildingService.assignBuildingToStaff(assignmentBuildingDTO.getBuildingId(), assignmentBuildingDTO.getStaffIds());

            // Set success message
            responseDTO.setMessage("Assign building successfully");
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            // Handle specific runtime errors
            responseDTO.setMessage("Assign building failed");
            responseDTO.setDetail(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

}
