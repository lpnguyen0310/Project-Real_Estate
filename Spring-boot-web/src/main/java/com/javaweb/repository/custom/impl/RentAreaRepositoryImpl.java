package com.javaweb.repository.custom.impl;


import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean deleteRentAreaByBuildingId(List<Long> buildingIds) {
        String sql = "DELETE FROM rentarea WHERE buildingid IN (:buildingIds)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("buildingIds", buildingIds);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteOneRentAreaByBuildingId(Long buildingId) {
        String sql = "DELETE FROM rentarea r where r.buildingid = :buildingId";
        Query query = entityManager.createNativeQuery(sql, RentAreaEntity.class);
        query.setParameter("buildingId", buildingId);
        query.executeUpdate();
        return true;
    }
}
