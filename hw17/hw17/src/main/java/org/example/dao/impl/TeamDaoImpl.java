package org.example.dao.impl;


import jakarta.persistence.EntityManager;
import org.example.base.dao.impl.BaseDaoImpl;
import org.example.dao.TeamDao;
import org.example.entity.Team;

public class TeamDaoImpl extends BaseDaoImpl<Team,Long> implements TeamDao {
    public TeamDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }
}
