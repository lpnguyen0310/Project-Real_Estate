package com.javaweb.repository.custom.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.repository.custom.AssignmentRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AssignmentRepositoryImpl implements AssignmentRepositoryCustom {


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public boolean deleteAssingmentByBuildingId(List<Long> buildingIds) {
        String sql = "DELETE FROM assignmentbuilding a where a.buildingid in (:buildingIds)";
        Query query = entityManager.createNativeQuery(sql, AssignmentBuildingEntity.class);
        query.setParameter("buildingIds", buildingIds);
        query.executeUpdate();
        return true;
    }
}
