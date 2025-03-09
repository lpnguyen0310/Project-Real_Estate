package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.TransactionResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService implements ITransactionService {



    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<TransactionResponseDTO> findAllTransaction(Long id) {
        List<TransactionEntity> transactionEntities = transactionRepository.findTransactionByCustomerId(id);
        System.out.println("===== Debug Transaction List =====");
        if (transactionEntities.isEmpty()) {
            System.out.println("Không có giao dịch nào cho khách hàng ID: " + id);
        } else {
            for (TransactionEntity transaction : transactionEntities) {
                System.out.println("Transaction ID: " + transaction.getId() +
                        ", Code: " + transaction.getCode() +
                        ", Note: " + transaction.getNote());
            }
        }
        List<TransactionResponseDTO> results = new ArrayList<>();
        for (TransactionEntity item : transactionEntities) {
            TransactionResponseDTO transactionResponseDTO = transactionConverter.toTransactionResponseDTO(item);
            results.add(transactionResponseDTO);
        }
        return results;
    }

    @Override
    public TransactionDTO createOrUpdateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity;


        if (transactionDTO.getId() != null) {
            // Chỉnh sửa giao dịch
            transactionEntity = transactionRepository.findById(transactionDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch"));
        } else {
            // Tạo mới giao dịch
            transactionEntity = new TransactionEntity();
            transactionEntity.setCustomerEntity(customerRepository.findById(transactionDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng")));
            transactionEntity.setCode(transactionDTO.getCode());
        }

        transactionEntity.setNote(transactionDTO.getNote()); // Cập nhật nội dung giao dịch
        transactionEntity = transactionRepository.save(transactionEntity); // Lưu vào database
        return modelMapper.map(transactionEntity, TransactionDTO.class); // Trả về DTO
    }
}
