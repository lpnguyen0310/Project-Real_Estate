package com.javaweb.repository.custom.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private void sqlWhereNormal(CustomerSearchBuilder builder, StringBuilder sql) {
        if (builder.getFullName() != null && !builder.getFullName().isEmpty()) {
            sql.append(" AND fullName LIKE '%").append(builder.getFullName()).append("%'");
        }
        if (builder.getEmail() != null && !builder.getEmail().isEmpty()) {
            sql.append(" AND email LIKE '%").append(builder.getEmail()).append("%'");
        }
        if (builder.getPhone() != null && !builder.getPhone().isEmpty()) {
            sql.append(" AND phone LIKE '%").append(builder.getPhone()).append("%'");
        }
        if (builder.getStatus() != null && !builder.getStatus().isEmpty()) {
            sql.append(" AND status = '").append(builder.getStatusForDatabase()).append("'");
        }
    }


    @Override
    public List<CustomerEntity> findAllCustomer(CustomerSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM customer WHERE 1=1");
        sqlWhereNormal(builder, sql);
        sql.append(" LIMIT ").append(pageable.getPageSize())
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }

    @Override
    public int countTotalBuildings(CustomerSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customer WHERE 1=1");
        sqlWhereNormal(builder, sql); // Tách điều kiện WHERE
        Query query = entityManager.createNativeQuery(sql.toString());
        return ((Number) query.getSingleResult()).intValue();
    }

    // Code here
}
