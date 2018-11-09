package com.example.sqltest.repository.impl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomerRepositoryImpl extends CommonRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getCustomerByCustomerId(String customerId) throws Exception{
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
            criteriaQuery.where(criteriaBuilder.equal(customerRoot.get("customerId"), customerId));
            Query query = entityManager.createQuery(criteriaQuery);
            return (Customer) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Customer> getCustomers() throws Exception{
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
