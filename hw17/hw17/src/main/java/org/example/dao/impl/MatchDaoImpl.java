package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.base.dao.impl.BaseDaoImpl;
import org.example.dao.MatchDao;
import org.example.entity.Match;
import org.example.entity.Season;
import org.example.entity.Team;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatchDaoImpl extends BaseDaoImpl<Match, Long> implements MatchDao {
    public MatchDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Match> getEntityClass() {
        return Match.class;
    }

    @Override
    public List<Object[]> findDerbies(Season season) {
        TypedQuery<Object[]> query = entityManager
                .createQuery("select m.homeTeam.name as hn, m.homeTeamGoals as hg," +
                                "  m.awayTeamGoals as ag , m.awayTeam.name as an " +
                                " from Match m " +
                                "where m.homeTeam.city = m.awayTeam.city " +
                                "and m.season.id = :pk " +
                                "group by hn, hg, ag, an " +
                                "order by sum(m.homeTeamGoals+m.awayTeamGoals)  desc "
                        , Object[].class);

        query.setParameter("pk", season.getId());

        return query.getResultList();
    }

    @Override
    public Integer calculateTeamPoints(Season season, Team team) {
        return Math.toIntExact((calculateHomeTeamPoints(season, team) + calculateAwayTeamPoints(season, team)));
    }

    @Override
    public Set<Long> findSeasonTeamsId(Season season) {
        List<Long> listId = findHomeTeamsId(season);
        listId.addAll(findAwayTeamsId(season));

        return new HashSet<>(listId);
    }

    private Long calculateHomeTeamPoints(Season season, Team team) {
        TypedQuery<Long> query = entityManager
                .createQuery("select sum (m.homeTeamPoints) from  Match m " +
                                "where m.season.id = :pk1 " +
                                "and m.homeTeam.id = :pk2 "
                        , Long.class);

        query.setParameter("pk1", season.getId());
        query.setParameter("pk2", team.getId());
        Long result = query.getSingleResult();
        if (result != null)
            return result;
        else return 0L;
    }

    private Long calculateAwayTeamPoints(Season season, Team team) {
        TypedQuery<Long> query = entityManager
                .createQuery("select sum (m.awayTeamPoints) from  Match m " +
                                "where m.season.id = :pk1 " +
                                "and m.awayTeam.id = :pk2 "
                        , Long.class);

        query.setParameter("pk1", season.getId());
        query.setParameter("pk2", team.getId());
        Long result = query.getSingleResult();
        if (result != null)
            return result;
        else return 0L;
    }

    private List<Long> findHomeTeamsId(Season season) {
        TypedQuery<Long> query = entityManager
                .createQuery("select m.homeTeam.id from Match m " +
                        "where m.season.id = :pk ", Long.class);
        query.setParameter("pk", season.getId());
        return query.getResultList();
    }

    private List<Long> findAwayTeamsId(Season season) {
        TypedQuery<Long> query = entityManager
                .createQuery("select m.awayTeam.id from Match m " +
                        "where m.season.id = :pk ", Long.class);
        query.setParameter("pk", season.getId());
        return query.getResultList();
    }
}
/*
    @Override
    public List<Match> derbies(Season season) {
        TypedQuery<Match> query = entityManager
                .createQuery("select m from Match m " +
                                "where m.homeTeam.city = m.awayTeam.city " +
                                "and m.season.id = :pk "
                        ,Match.class);

        query.setParameter("pk",season.getId());

        return query.getResultList();
    }

    @Override
    public List<Match> derbiesWithMostGoals(List<Match> derbies) {
        return null;
    }*/
