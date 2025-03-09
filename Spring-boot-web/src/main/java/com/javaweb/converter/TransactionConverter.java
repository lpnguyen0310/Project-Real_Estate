package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerResponseDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.TransactionResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;
    public TransactionDTO convertToDTO(TransactionEntity transactionEntity){
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        transactionDTO.setCode(transactionEntity.getCode());
        return transactionDTO;
    }

    public TransactionEntity convertToEntity(TransactionDTO transactionDTO){
        TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
        transactionEntity.setCode(transactionDTO.getCode());
        return transactionEntity;
    }


    public TransactionResponseDTO toTransactionResponseDTO(TransactionEntity item) {
        TransactionResponseDTO transactionResponseDTO = modelMapper.map(item, TransactionResponseDTO.class);
        transactionResponseDTO.setCode(item.getCode());
        return transactionResponseDTO;
    }
}
