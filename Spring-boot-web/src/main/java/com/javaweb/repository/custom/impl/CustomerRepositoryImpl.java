package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.DataUtil;
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
            sql.append(" AND c.fullname LIKE '%").append(builder.getFullName()).append("%'");
        }
        if (builder.getEmail() != null && !builder.getEmail().isEmpty()) {
            sql.append(" AND c.email LIKE '%").append(builder.getEmail()).append("%'");
        }
        if (builder.getPhone() != null && !builder.getPhone().isEmpty()) {
            sql.append(" AND c.phone LIKE '%").append(builder.getPhone()).append("%'");
        }
        if (builder.getStatus() != null && !builder.getStatus().isEmpty()) {
            sql.append(" AND c.status LIKE '%").append(builder.getStatus()).append("%'");
        }
    }


    private void sqlJoin(CustomerSearchBuilder builder, StringBuilder sql) {
        Long staffId = builder.getStaffId();
        if (DataUtil.checkData(staffId)) {
            sql.append(" JOIN assignmentcustomer ac ON c.id = ac.customerid ");
        }
    }

    private void whereClause(CustomerSearchBuilder builder, StringBuilder sql) {
        Long staffId = builder.getStaffId();
        if (DataUtil.checkData(staffId)) {
            sql.append(" AND ac.staffid = " + staffId);
        }
    }


    @Override
    public List<CustomerEntity> findAllCustomer(CustomerSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer as c \r\n");
        sqlJoin(builder, sql); // Join bảng nếu có
        StringBuilder where = new StringBuilder(" WHERE c.is_active = '1'");

        sqlWhereNormal(builder, where); // Tách điều kiện WHERE
        whereClause(builder, where);
        sql.append(where).append(" group by c.id \r\n");
        sql.append(" LIMIT ").append(pageable.getPageSize())
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }

    @Override
    public int countTotalBuildings(CustomerSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customer as c \r\n");
        sqlJoin(builder, sql); // Join bảng nếu có
        StringBuilder where = new StringBuilder(" WHERE c.is_active = '1'");
        sqlWhereNormal(builder, where); // Tách điều kiện WHERE
        whereClause(builder, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString());
        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public void deleteCustomer(List<Long> id) {
        String sql = "UPDATE customer SET is_active = '0' WHERE id in (:id)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public CustomerEntity findCustomerById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = :id";
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        query.setParameter("id", id);
        return (CustomerEntity) query.getSingleResult();
    }

    @Override
    public CustomerEntity findOneCustomerByPhoneAndIs_active(String phone, int is_active) {
        // Kiểm tra phone tồn tại chưa và is_active = 1
        // Phone đuọược truyền vào từ CustomerDTO
        String sql = "SELECT * FROM customer WHERE phone = :phone AND is_active = 1";
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        query.setParameter("phone", phone);
        return (CustomerEntity) query.getSingleResult();
    }

    // Code here
}
