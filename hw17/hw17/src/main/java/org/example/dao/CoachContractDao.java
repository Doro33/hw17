package org.example.dao;

import org.example.base.dao.BaseDao;
import org.example.entity.CoachContract;

import java.util.List;

public interface CoachContractDao extends BaseDao<CoachContract,Long> {
    List<Object[]> mostExpensiveCoaches();
}
