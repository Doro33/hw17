package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import org.example.base.dao.impl.BaseDaoImpl;
import org.example.dao.CityDao;
import org.example.entity.City;

import java.util.List;

public class CityDaoImpl extends BaseDaoImpl<City,Long> implements CityDao {
    public CityDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public List<Object[]> countTeams() {
        return entityManager
                .createQuery("select t.city.name , count(t.city) from Team t group by t.city.name"
        ,Object[].class).getResultList();
    }
}

//System.out.println(Arrays.toString(result.get(0)));
//System.out.println(Arrays.toString(result.get(1)));

//System.out.println(Arrays.deepToString(result.toArray()));