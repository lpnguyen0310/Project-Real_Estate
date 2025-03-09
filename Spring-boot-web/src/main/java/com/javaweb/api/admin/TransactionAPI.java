package com.javaweb.api.admin;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/api/admin/transactions")
    public ResponseEntity<?> createOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        try {
            TransactionDTO savedTransaction = transactionService.createOrUpdateTransaction(transactionDTO);
            return ResponseEntity.ok(savedTransaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi lưu giao dịch: " + e.getMessage());
        }
    }
}
