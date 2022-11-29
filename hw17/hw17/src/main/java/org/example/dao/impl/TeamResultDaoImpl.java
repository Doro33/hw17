package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.base.dao.impl.BaseDaoImpl;
import org.example.dao.MatchDao;
import org.example.dao.TeamDao;
import org.example.dao.TeamResultDao;
import org.example.entity.Season;
import org.example.entity.Team;
import org.example.entity.TeamResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamResultDaoImpl extends BaseDaoImpl<TeamResult, Long> implements TeamResultDao {
    public TeamResultDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    MatchDao matchDao = new MatchDaoImpl(entityManager);
    TeamDao teamDao = new TeamDaoImpl(entityManager);

    @Override
    public Class<TeamResult> getEntityClass() {
        return TeamResult.class;
    }

    @Override
    public void saveAllResults(Season season) {
        Set<Long> teamIds = matchDao.findSeasonTeamsId(season);
        Set<Team> teams = new HashSet<>();
        teamIds.forEach(Long -> teams.add(teamDao.findById(Long)));

        List<TeamResult> teamResults = new ArrayList<>();
        teams.forEach(team -> teamResults
                .add(new TeamResult(team, season, matchDao.calculateTeamPoints(season, team))));

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            teamResults.forEach(this::save);
            transaction.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public List<Object[]> showTable(Season season) {
        TypedQuery<Object[]> query = entityManager
                .createQuery("select t.team.name , t.points from TeamResult t " +
                                "order by t.points desc "
                        , Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> findChampion(Season season) {
        TypedQuery<Object[]> query = entityManager
                .createQuery("select t.team.name , t.points from TeamResult t " +
                                "where t.points = :pk1 " +
                                "and t.season.id = :pk2"
                        , Object[].class);

        Integer maxPoint = entityManager
                .createQuery("select max (t.points) from TeamResult t " +
                        "where t.season.id = :pk3 ", Integer.class)
                .setParameter("pk3", season.getId())
                .getResultList().get(0);

        query.setParameter("pk1", maxPoint);
        query.setParameter("pk2",season.getId());

        return query.getResultList();
    }
}
