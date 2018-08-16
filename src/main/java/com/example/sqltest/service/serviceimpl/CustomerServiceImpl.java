package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Implementation of CustomerService
 *
 * @see CustomerService
 * @author Jfredricks
 * @version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Creates a new row and saves it
     * @param customer is saved to Customer table
     *//*
    public void create(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    *//**
     * @return lists all the rows in Customer table
     *//*
    public List<Customer> listTable() {
        return customerRepository.findAll();
    }*/

    public Customer getCustomerByCustomerId(String customerId) {
        return customerRepository.getCustomerByCustomerId(customerId);
    }

    /**
     * Updates a row in Customer table
     * @param customer contains values to be updated
     * @return saved row
     *//*
    public Customer updateTable(Customer customer) {
        try {
            if (recordExists(customer.getCustomerId())) {
                Customer foundByCustomerId = customerRepository.findByCustomerId(customer.getCustomerId());
                foundByCustomerId.setAccountNum(customer.getAccountNum());
                foundByCustomerId.setCreatedBy(customer.getCreatedBy());
                foundByCustomerId.setStatus(customer.getStatus());
                return customerRepository.save(foundByCustomerId);
            }
        }catch(Exception e) {

        }
    }

    *//**
     * Deletes rows based on customer ID
     * @param cId is the customer ID of the row to be deleted
     *//*
    public void deleteRow(String cId) {
        Customer foundByCustomerId = customerRepository.findByCustomerId(cId);
        customerRepository.delete(foundByCustomerId);
    }

    *//**
     * Checks if a row exists in the table
     * @param cId is the customer ID of the row
     * @return true or false
     *//*
    //TODO: If record exists return true or false
    public boolean recordExists(String cId) {
        return customerRepository.findByCustomerId(cId) != null;
    }*/
}
