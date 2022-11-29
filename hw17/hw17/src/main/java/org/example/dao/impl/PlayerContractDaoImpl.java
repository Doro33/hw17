package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.base.dao.impl.BaseDaoImpl;
import org.example.dao.PlayerContractDao;
import org.example.entity.PlayerContract;
import org.example.entity.Season;

import java.util.List;

public class PlayerContractDaoImpl extends BaseDaoImpl<PlayerContract,Long> implements PlayerContractDao {
    public PlayerContractDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<PlayerContract> getEntityClass() {
        return PlayerContract.class;
    }

    @Override
    public List<Object[]> showBySalary(Season season) {
        TypedQuery<Object[]> query = entityManager
                .createQuery("select p.player.name , p.salary from PlayerContract p " +
                                "where p.season.id = :pk " +
                                "order by p.salary desc "
                        , Object[].class);
        query.setParameter("pk",season.getId());
        return query.getResultList();
    }
}
