package com.example.sqltest.repository;

import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.web.models.CustomerDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository which extends CrudRepository
 */

public interface CustomerRepository {

    Customer getCustomerByCustomerId(String cId) throws Exception;

    List<Customer> getCustomers() throws Exception;

    void save(Customer customer) throws Exception;

    void update(Customer customer) throws Exception;
}
