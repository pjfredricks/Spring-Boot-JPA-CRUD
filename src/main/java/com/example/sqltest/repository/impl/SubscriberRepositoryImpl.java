package com.example.sqltest.repository.impl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.SubscriberRepository;
import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.repository.model.Subscriber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Subscriber> getSubscribers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
        Root<Subscriber> subscriberRoot = criteriaQuery.from(Subscriber.class);
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<Subscriber> getSubscriberByCustomerId(String customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
        Root<Subscriber> subscriberRoot = criteriaQuery.from(Subscriber.class);
        criteriaQuery.where(criteriaBuilder.equal(subscriberRoot.get("customerId"), customerId));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
