package com.example.sqltest.service;

import com.example.sqltest.repository.model.Customer;

import java.util.List;

/**
 * Defines the various services performed on customer table
 * @see Customer
 * @author Jfredricks
 * @version 1.0
 */
public interface CustomerService {

    /**
     * Creates a new row in customer table
     * @param customer is the object with values to be updated
     */
     Customer create(Customer customer) throws Exception;

    /**
     * Retrieves all records in customer table
     * @return List
     * @throws Exception
     */
    List<Customer> getCustomers() throws Exception;

    /**
     * Retrieves Customer record by customerId
     * @param customerId
     * @return Customer
     * @throws Exception
     */
    Customer getCustomerByCustomerId(String customerId) throws Exception;

     /**
     * Updates a row with new values
     * @param customer is the object with values to be updated
     */
     void updateTable(String customerId, Customer customer) throws Exception;

     /**
     * Deletes a row from cusotmer table
     * @param cId is the customerId of the row to be deleted
     */
    void deleteRow(String cId)  throws Exception ;
}
