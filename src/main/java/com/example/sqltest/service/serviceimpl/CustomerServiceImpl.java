package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.service.CustomerService;
import com.example.sqltest.web.models.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

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


    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates a new row and saves it
     * @param customer is saved to Customer table
     */
     public Customer create( Customer customer) throws Exception {
         try {
             if (!recordExists(customer.getCustomerId())) {
                 customerRepository.save(customer);
             }
         } catch (Exception e) {
             throw new Exception(e.getCause());
         }
         return customer;
     }

    /**
     * @return lists all the rows in Customer table
     */
    public List<Customer> getCustomers() throws Exception {
        return customerRepository.getCustomers();
    }

    public Customer getCustomerByCustomerId(String customerId) throws Exception{
        return customerRepository.getCustomerByCustomerId(customerId);
    }

    /**
     * Updates a row in Customer table
     * @param customer contains values to be updated
     * @return saved row
     */
        public Customer updateTable(String customerId, Customer customer) throws Exception {
            try {
                if (recordExists(customerId)) {
                    Customer foundByCustomerId = customerRepository.getCustomerByCustomerId(customerId);
                    foundByCustomerId.setAccountNum(customer.getAccountNum());
                    foundByCustomerId.setCreatedBy(customer.getCreatedBy());
                    foundByCustomerId.setStatus(customer.getStatus());
                    customerRepository.update(customer);
                }
            }catch (Exception e) {
                throw new Exception(e.getCause());
            }
            return customer;
        }

    /**
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
     */
    public boolean recordExists(String cId) throws Exception {
        return customerRepository.getCustomerByCustomerId(cId) != null;
    }
}
