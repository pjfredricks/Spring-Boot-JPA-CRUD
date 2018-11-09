package com.example.sqltest.repository.impl;

import com.example.sqltest.repository.SubscriberRepository;
import com.example.sqltest.repository.model.Subscriber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubscriberRepositoryImpl extends CommonRepositoryImpl implements SubscriberRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Subscriber> getSubscribers()  throws Exception{
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
            Root<Subscriber> subscriberRoot = criteriaQuery.from(Subscriber.class);
            Query query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    public List<Subscriber> getSubscriberByCustomerId(String customerId)  throws Exception{
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
            Root<Subscriber> subscriberRoot = criteriaQuery.from(Subscriber.class);
            criteriaQuery.where(criteriaBuilder.equal(subscriberRoot.get("customerId"), customerId));
            Query query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Subscriber getSubscriberByServiceNum(String serviceNum)  throws Exception{
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
            Root<Subscriber> root = criteriaQuery.from(Subscriber.class);
            Query query = entityManager.createQuery(criteriaQuery);
            return (Subscriber) query.getSingleResult();
        }
        catch (NoResultException e) {
            return new Subscriber();
        }
        catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }
}
