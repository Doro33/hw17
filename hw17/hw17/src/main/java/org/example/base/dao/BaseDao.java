package org.example.base.dao;

import jakarta.persistence.EntityManager;
import org.example.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E extends BaseEntity<ID>, ID extends Serializable> {

    E saveOrUpdate(E e);

    E save(E e);

    E update(E e);

    void delete(E e);

    E findById(ID id);

    List<E> findAll();

    boolean isExistsById(ID id);

    Long countAll();


    EntityManager getEntityManager();
}