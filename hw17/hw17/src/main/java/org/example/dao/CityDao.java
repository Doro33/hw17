package org.example.dao;

import org.example.base.dao.BaseDao;
import org.example.entity.City;

import java.util.List;

public interface CityDao extends BaseDao<City,Long> {
    List<Object[]> countTeams();
}
