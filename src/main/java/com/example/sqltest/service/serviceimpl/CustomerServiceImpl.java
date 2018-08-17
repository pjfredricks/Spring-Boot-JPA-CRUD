package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of CustomerService
 *
 * @author Jfredricks
 * @version 1.0
 * @see CustomerService
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Creates a new row and saves it
     *
     * @param customer is saved to Customer table
     */
    public Customer create(Customer customer) throws Exception {
        if (!recordExists(customer.getCustomerId())) {
            customerRepository.save(customer);
            return customer;
        }
        return null;
    }

    /**
     * @return lists all the rows in Customer table
     */
    public List<Customer> getCustomers() throws Exception {
        return customerRepository.getCustomers();
    }

    /**
     * Retrieves Customer by customerId
     *
     * @param customerId
     * @return customer
     * @throws Exception
     */
    public Customer getCustomerByCustomerId(String customerId) throws Exception {
        return customerRepository.getCustomerByCustomerId(customerId);
    }

    /**
     * Updates a row in Customer table
     *
     * @param customer contains values to be updated
     * @return saved row
     */
    public void updateTable(Customer customer) throws Exception {
        if (recordExists(customer.getCustomerId())) {
            Customer foundByCustomerId = customerRepository.getCustomerByCustomerId(customer.getCustomerId());
            foundByCustomerId.setAccountNum(customer.getAccountNum());
            foundByCustomerId.setCreatedBy(customer.getCreatedBy());
            foundByCustomerId.setStatus(customer.getStatus());
            customerRepository.update(customer);
        }
    }

    /**
     * Deletes rows based on customer ID
     *
     * @param customerId is the customer ID of the row to be deleted
     */
    public void deleteRow(String customerId) throws Exception {
        Customer foundByCustomerId = customerRepository.getCustomerByCustomerId(customerId);
        if (foundByCustomerId != null) {
            customerRepository.delete(foundByCustomerId);
        } else {
            throw new Exception("No Record Found");
        }
    }

    /**
     * Checks if a row exists in the table
     *
     * @param cId is the customer ID of the row
     * @return true or false
     */
    private boolean recordExists(String cId) throws Exception {
        return customerRepository.getCustomerByCustomerId(cId) != null;
    }
}
