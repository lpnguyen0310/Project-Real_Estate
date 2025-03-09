package com.javaweb.repository.custom.impl;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.custom.TransactionRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<TransactionEntity> findTransactionByCustomerId(Long id) {
        String sql = "SELECT * FROM transaction WHERE customerid = :id";
        Query query = entityManager.createNativeQuery(sql, TransactionEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
