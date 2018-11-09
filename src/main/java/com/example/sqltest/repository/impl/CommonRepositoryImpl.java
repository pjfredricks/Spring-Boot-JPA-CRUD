package com.example.sqltest.repository.impl;

import com.example.sqltest.repository.CommonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class CommonRepositoryImpl implements CommonRepository {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public <T> void save(T t) throws Exception {
        try {
            entityManager.persist(t);
            entityManager.flush();
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    @Transactional
    public <T> void update(T t) throws Exception {
        try {
            entityManager.merge(t);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    @Transactional
    public <T> void delete(T t) throws Exception {
        try {
            entityManager.remove(t);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }
}
