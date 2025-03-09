package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.TransactionResponseDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionResponseDTO> findAllTransaction(Long id);

    TransactionDTO createOrUpdateTransaction(TransactionDTO transactionDTO);

    void deleteTransaction(Long id);
}
