package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.service.CustomerService;
import com.example.sqltest.web.model.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    ObjectMapper mapper;

    /**
     * Creates a new row and saves it
     *
     * @param customerDTO is saved to Customer table
     */
    public CustomerDTO create(CustomerDTO customerDTO) throws Exception {
        if (!recordExists(customerDTO.getCustomerId())) {
            customerRepository.save(new Customer(customerDTO.getCustomerId()
                    , customerDTO.getAccountNum()
                    , customerDTO.getStatus()
                    , customerDTO.getCreatedBy()));
            return customerDTO;
        }
        throw new Exception("Record already exists");
    }

    /**
     * @return lists all the rows in Customer table
     */
    public List<CustomerDTO> getCustomers() throws Exception {
    return customerRepository.getCustomers().stream()
            .map(CustomerDTO::map)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves Customer by customerId
     *
     * @param customerId
     * @return customer
     * @throws Exception
     */
    public CustomerDTO getCustomerByCustomerId(String customerId) throws Exception {
        CustomerDTO customerDTO = mapper.convertValue(customerRepository.getCustomerByCustomerId(customerId), CustomerDTO.class);
        return customerDTO;
    }

    /**
     * Updates a row in Customer table
     *
     * @param customerDTO contains values to be updated
     * @return saved row
     */
    public void updateTable(CustomerDTO customerDTO) throws Exception {
        if (recordExists(customerDTO.getCustomerId())) {
            Customer foundByCustomerId = customerRepository.getCustomerByCustomerId(customerDTO.getCustomerId());
            foundByCustomerId.setAccountNum(customerDTO.getAccountNum());
            foundByCustomerId.setCreatedBy(customerDTO.getCreatedBy());
            foundByCustomerId.setStatus(customerDTO.getStatus());
            customerRepository.update(mapper.convertValue(customerDTO, Customer.class));
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
     * @param customerId is the customer ID of the row
     * @return true or false
     */
    private boolean recordExists(String customerId) throws Exception {
        return customerRepository.getCustomerByCustomerId(customerId) != null;
    }
}
