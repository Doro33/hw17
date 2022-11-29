package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.base.dao.impl.BaseDaoImpl;
import org.example.dao.CoachContractDao;
import org.example.entity.CoachContract;

import java.util.List;

public class CoachContractDaoImpl extends BaseDaoImpl<CoachContract, Long> implements CoachContractDao {
    public CoachContractDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CoachContract> getEntityClass() {
        return CoachContract.class;
    }

   @Override
    public List<Object[]> mostExpensiveCoaches() {
        TypedQuery<Object[]> query = entityManager
                .createQuery("select c.coach.name , c.salary from CoachContract c " +
                                "where c.salary = :pk"
                        , Object[].class);

        double maxSalary = entityManager
                .createQuery("select max (c.salary) from CoachContract c", double.class)
                .getResultList().get(0);

        query.setParameter("pk", maxSalary);

        return query.getResultList();
    }
}