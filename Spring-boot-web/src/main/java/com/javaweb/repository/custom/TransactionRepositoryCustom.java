package com.javaweb.repository.custom;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<TransactionEntity> findTransactionByCustomerId(Long id);

    void deleteTransaction(Long id);
}
