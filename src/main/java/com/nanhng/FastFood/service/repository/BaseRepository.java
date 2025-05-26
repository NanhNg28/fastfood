package com.nanhng.FastFood.service.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class BaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void flush() {
        entityManager.flush();
    }

    protected void detach(Object object) {
        entityManager.detach(object);
    }

    protected JPAQueryFactory query() {
        return new JPAQueryFactory(entityManager);
    }

}
