package com.example.sqltest.sql.service.serviceimpl;

import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.repository.CustomerRepository;
import com.example.sqltest.sql.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Implementation of CustomerService
 *
 * @author Jfredricks
 * @version 1.0
 */
@org.springframework.stereotype.Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository cRepo;

    /**
     * Creates a new row and saves it
     * @param customer is saved to Customer table
     */
    public void create(@RequestBody Customer customer) {
        cRepo.save(customer);
    }

    /**
     * @return lists all the rows in Customer table
     */
    public Iterable<Customer> listTable() {
        return cRepo.findAll();
    }

    /**
     * Updates a row in Customer table
     * @param customer contains values to be updated
     * @return saved row
     */
    public Customer updateTable(@RequestBody Customer customer) {
        Customer foundByCustomerId = cRepo.findByCustomerId(customer.getCustomerId());
        foundByCustomerId.setAccountNum(customer.getAccountNum());
        foundByCustomerId.setCreatedBy(customer.getCreatedBy());
        foundByCustomerId.setStatus(customer.getStatus());
        return cRepo.save(foundByCustomerId);
    }

    /**
     * Deletes rows based on customer ID
     * @param cId is the customer ID of the row to be deleted
     */
    public void deleteRow(String cId) {
        Customer foundByCustomerId = cRepo.findByCustomerId(cId);
        cRepo.delete(foundByCustomerId);
    }

    /**
     * Checks if a row exists in the table
     * @param cId is the customer ID of the row
     * @return true or false
     */
    public boolean recordExists(String cId) {
        return cRepo.findByCustomerId(cId) != null;
    }
}
