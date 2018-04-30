package com.example.sqltest.sql.repository;

import com.example.sqltest.sql.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which extends CrudRepository
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    /**
     * Finds a row with matching cusotmerId
     *
     * @param cId is the customerId to be searched
     * @return a row with matching cId
     */
    Customer findByCustomerId(String cId);
}
