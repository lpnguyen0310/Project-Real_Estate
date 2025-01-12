package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/building")
    public ResponseEntity<?> createOrUpdateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        try {
            // Kiểm tra lỗi validate từ phía client
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }

            // Gọi service để thêm mới hoặc cập nhật tòa nhà
            BuildingDTO savedBuilding = buildingService.addOrUpdateBuilding(buildingDTO);

            // Kiểm tra kết quả xử lý
            if (savedBuilding == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation failed. Unable to save building.");
            }

            // Trả về kết quả thành công
            return ResponseEntity.ok(savedBuilding);
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // API xóa tòa nhà
    @PostMapping("/api/building/{id} ")
    public ResponseEntity<?> deleteBuilding(@RequestBody List<Long> ids) {
       if(ids.size() == 0){
           ResponseDTO responseDTO = new ResponseDTO();
           responseDTO.setMessage("Please choose at least 1 building to delete");
              return ResponseEntity.badRequest().body(responseDTO);
       }
       else {
           buildingService.deleteAllByIdIn(ids);
       }

       return ResponseEntity.ok("Xóa thành công");
    }

    @DeleteMapping("/api/building/{ids}")
    public ResponseEntity<?> deleteBuildings(@PathVariable List<Long> ids) {
       if (ids.size() == 0) {
           ResponseDTO responseDTO = new ResponseDTO();
           responseDTO.setMessage("Please choose at least 1 building to delete");
           return ResponseEntity.badRequest().body(responseDTO);
       } else {
           buildingService.deleteAllByIdIn(ids);
           ResponseDTO responseDTO = new ResponseDTO();
           responseDTO.setMessage("Delete successfully");
           return ResponseEntity.ok().body(responseDTO);
       }
    }

    // API lấy danh sách tòa nhà theo id tòa nhà và trả về danh sách nhân viên được gán vào tòa nhà
    @GetMapping("/api/building/{buildingId}/staffs")
    public ResponseEntity<List<StaffResponseDTO>> getStaffsByBuilding(@PathVariable Long buildingId) {
        List<StaffResponseDTO> response = buildingService.getStaffsByBuilding(buildingId);
        return ResponseEntity.ok(response);
    }






}
