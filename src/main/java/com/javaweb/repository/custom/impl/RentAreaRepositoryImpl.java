package com.javaweb.repository.custom.impl;


import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean deleteRentAreaByBuildingId(Long buildingId) {
      String sql = "DELETE FROM rentarea r where r.buildingid = :buildingId";
        Query query = entityManager.createNativeQuery(sql, RentAreaEntity.class);
        query.setParameter("buildingId", buildingId);
        query.executeUpdate();
        return true;
    }
}
