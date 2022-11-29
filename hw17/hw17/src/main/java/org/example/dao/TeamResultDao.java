package org.example.dao;

import org.example.base.dao.BaseDao;
import org.example.entity.Season;
import org.example.entity.TeamResult;

import java.util.List;

public interface TeamResultDao extends BaseDao<TeamResult,Long> {
    void saveAllResults(Season season);

    List<Object[]> showTable(Season season);


    List<Object[]> findChampion(Season season);
}
