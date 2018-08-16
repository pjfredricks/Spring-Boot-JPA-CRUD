package com.example.sqltest.repository;

import com.example.sqltest.repository.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which extends CrudRepository
 */

public interface CustomerRepository {

    Customer getCustomerByCustomerId(String cId);
}
