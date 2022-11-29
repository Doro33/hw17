package org.example.dao;

import org.example.base.dao.BaseDao;
import org.example.entity.Match;
import org.example.entity.Season;
import org.example.entity.Team;

import java.util.List;
import java.util.Set;

public interface MatchDao extends BaseDao<Match,Long> {
    List<Object[]> findDerbies(Season season);

    Integer calculateTeamPoints(Season season, Team team);

    Set<Long> findSeasonTeamsId(Season season);
}
    /*List<Match> derbies(Season season);

    List<Match> derbiesWithMostGoals(List<Match> derbies);*/