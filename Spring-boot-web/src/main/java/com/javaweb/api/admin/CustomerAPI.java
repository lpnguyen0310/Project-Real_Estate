package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.service.ICustormerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerAPI {

    @Autowired
    private ICustormerService custormerService;


    @PostMapping("/api/customers")
    public ResponseEntity<?> sendContact(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        try {
            // Kiểm tra lỗi validate từ phía client
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }
            // Lưu d liệu vào database
            CustomerDTO savedCustomer = custormerService.save(customerDTO);
            // Kiểm tra kết quả xử lý
            if (savedCustomer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation failed. Unable to save customer.");
            }
            // Trả về kết quả thành công
            return ResponseEntity.ok(savedCustomer);


        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
