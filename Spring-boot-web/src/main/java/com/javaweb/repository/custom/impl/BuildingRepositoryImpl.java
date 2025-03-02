package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.DataUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    private void sqlJoin(BuildingSearchBuilder builder, StringBuilder joinClauses) {
        Long staffId = builder.getStaffId();
        if(DataUtil.checkData(staffId)) {
            joinClauses.append(" join assignmentbuilding ab on b.id = ab.buildingid ");
        }
        Long rentAreaFrom = builder.getAreaFrom();
        Long rentAreaTo = builder.getAreaTo();
        if (DataUtil.checkData(rentAreaFrom) || DataUtil.checkData(rentAreaTo)) {
            joinClauses.append(" join rentarea ra on b.id = ra.buildingid ");
        }
    }

    private void sqlWhereNormal(BuildingSearchBuilder builder,StringBuilder whereClause) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item: fields) {
                item.setAccessible(true);
                String fieldName = item.getName();

                // Tách riêng xử lý cho typeCode để xử lý bằng LIKE
                if ("typeCode".equals(fieldName)) {
                    List<String> typeCode = (List<String>) item.get(builder);
                    if (typeCode != null && !typeCode.isEmpty()) {
                        whereClause.append(" and (");
                        // Duyệt qua từng phần tử trong typeCode để xử lý
                        for (int i = 0; i < typeCode.size(); i++) {
                            if (i > 0) whereClause.append(" or ");
                            whereClause.append("b.type like '%").append(typeCode.get(i)).append("%'");
                        }
                        whereClause.append(")");
                    }
                    continue; // Bỏ qua các xử lý tiếp theo cho typeCode
                }
                if(!fieldName.equals("staffId")  && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(builder);
                    if(value != null) {
                        if(item.getType().getName().equals("java.lang.String")) {
                            whereClause.append(" and b." + fieldName + " like '%" + value + "%'");
                        }
                        else  if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            whereClause.append(" and b." + fieldName + " = " + value);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO: handle exception
        }
    }

    private void sqlWhereSpecial(BuildingSearchBuilder builder, StringBuilder whereClause) {
        Long staffId = builder.getStaffId();
        if(DataUtil.checkData(staffId)) {
            whereClause.append(" and ab.staffid = " + staffId);
        }

        Long rentAreaFrom = builder.getAreaFrom();
        Long rentAreaTo = builder.getAreaTo();
        if (DataUtil.checkData(rentAreaFrom)) {
            whereClause.append(" and ra.value >= " + rentAreaFrom);
        }
        if(DataUtil.checkData(rentAreaTo)) {
            whereClause.append(" and ra.value <= " + rentAreaTo);
        }
        Long rentPriceFrom = builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        if (DataUtil.checkData(rentPriceFrom)) {
            whereClause.append(" and b.rentprice >= " + rentPriceFrom);
        }
        if (DataUtil.checkData(rentPriceTo)) {
            whereClause.append(" and b.rentprice <= " + rentPriceTo);
        }

    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select b.* from building b \r\n");
        sqlJoin(builder, sql);
        StringBuilder where = new StringBuilder(" where 1 = 1");
        sqlWhereNormal(builder, where);
        sqlWhereSpecial(builder, where);
        sql.append(where).append(" group by b.id \r\n");

        // Áp dụng LIMIT và OFFSET để phân trang
        sql.append(" limit ").append(pageable.getPageSize()).append("\n")
                .append(" offset ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public BuildingEntity findBuildingById(Long id) {
        StringBuilder sql = new StringBuilder("select * from building b where b.id = :id");
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        query.setParameter("id", id);
        return (BuildingEntity) query.getSingleResult();
    }

    @Override
    public void deleteBuildingById(List<Long> id) {
        String sql = "delete from building where id in (:id)";
        Query query = entityManager.createNativeQuery(sql,BuildingEntity.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public int countTotalBuildings( BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("select count(*) from building b \r\n");
        sqlJoin(builder, sql);
        StringBuilder where = new StringBuilder(" where 1 = 1");
        sqlWhereNormal(builder, where);
        sqlWhereSpecial(builder, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString());
        return ((Number) query.getSingleResult()).intValue();
    }




}
