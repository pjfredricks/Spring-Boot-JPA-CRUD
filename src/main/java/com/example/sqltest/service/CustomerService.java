package com.example.sqltest.service;

import com.example.sqltest.repository.model.Customer;
import org.springframework.web.bind.annotation.RequestBody;

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
    /*void create(@RequestBody Customer customer);

    *//**
     * Lists out all rows in customer table
     *//*
    List<Customer> listTable();*/

    Customer getCustomerByCustomerId(String customerId);

  /*  *//**
     * Updates a row with new values
     * @param customer is the object with values to be updated
     *//*
    Customer updateTable(@RequestBody Customer customer);

    *//**
     * Deletes a row from cusotmer table
     * @param cId is the customerId of the row to be deleted
     *//*
    void deleteRow(String cId);

    *//**
     * Checks if a record exists or not
     * @param cId is the customerId to be checked
     * @return true or false
     *//*
    boolean recordExists(String cId);*/
}
