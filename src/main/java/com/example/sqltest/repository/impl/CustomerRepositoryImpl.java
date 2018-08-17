package com.example.sqltest.repository.impl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

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
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Customer customer) throws Exception{
        try{
            entityManager.persist(customer);
            entityManager.flush();
        }catch (Exception e) {
          throw new Exception(e.getCause());
        }
    }

    @Override
    @Transactional
    public void update(Customer customer) throws Exception{
        try{
            entityManager.merge(customer);
        }catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @Override
    @Transactional
    public void delete(Customer customer) throws Exception {
        try{
            entityManager.remove(customer);
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }
}
