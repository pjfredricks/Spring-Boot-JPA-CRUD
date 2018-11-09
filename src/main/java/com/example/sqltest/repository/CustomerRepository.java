package com.example.sqltest.repository;

import com.example.sqltest.repository.model.Customer;

import java.util.List;

/**
 * Repository which extends CrudRepository
 */

public interface CustomerRepository extends CommonRepository {

    Customer getCustomerByCustomerId(String cId) throws Exception;

    List<Customer> getCustomers() throws Exception;
}
