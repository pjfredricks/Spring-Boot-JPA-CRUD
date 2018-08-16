package com.example.sqltest.repository.impl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.SubscriberRepository;
import com.example.sqltest.repository.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {

    @PersistenceContext
    EntityManager entityManager;

   /* @Override
    public Customer getCustomerByCustomerId(String customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        criteriaQuery.where(criteriaBuilder.equal(customerRoot.get("customer_id"), customerId));
        Query query = entityManager.createQuery(criteriaQuery);
        return (Customer) query.getSingleResult();
    }*/
}
