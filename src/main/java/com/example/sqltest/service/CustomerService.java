package com.example.sqltest.service;

import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.web.model.CustomerDTO;

import java.util.List;

/**
 * Defines the various services performed on customer table
 *
 * @author Jfredricks
 * @version 1.0
 * @see Customer
 */
public interface CustomerService {

    CustomerDTO create(CustomerDTO customer) throws Exception;

    List<CustomerDTO> getCustomers() throws Exception;

    CustomerDTO getCustomerByCustomerId(String customerId) throws Exception;

    void updateTable(CustomerDTO customerDTO) throws Exception;

    void deleteRow(String cId) throws Exception;
}
