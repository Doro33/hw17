package org.example.dao;

import org.example.base.dao.BaseDao;
import org.example.entity.PlayerContract;
import org.example.entity.Season;

import java.util.List;

public interface PlayerContractDao extends BaseDao<PlayerContract,Long> {
    List<Object[]> showBySalary(Season season);
}
