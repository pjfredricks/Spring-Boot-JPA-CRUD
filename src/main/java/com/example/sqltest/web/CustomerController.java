package com.example.sqltest.web;

import com.example.sqltest.exception.DbException;
import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Implements all CRUD Operations for customer table
 *
 * @author Jfredricks
 * @version 1.0
 */
@Api
@RestController
@RequestMapping("db/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Lists all rows in customer table
     * @return ResponseEntity
     */
    @ApiOperation("Retrieves all record from Customer Table")
    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() throws Exception {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    /**
     * Retrieves a record from Customer table by CustomerId
     * @param customerId
     * @return ResponseEntity
     * @throws Exception
     */
    @ApiOperation("Retrieves a record from Customer table by CustomerId")
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerByCustomerId(@RequestParam String customerId) throws Exception {
        return new ResponseEntity<>(customerService.getCustomerByCustomerId(customerId), HttpStatus.OK);
    }

    /**
     * Creates a new row if the values are validated successfully
     * @param customer is the row passed as parameter to this method
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation("Adds a new record to Customer table")
    @PostMapping("")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) throws Exception {
        if (customer.getStatus().equalsIgnoreCase("LEGACY") || customer.getStatus().equalsIgnoreCase("MIGRATED")) {
            return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Updates an existing row based on customer_id
     * @param customer is the row retrieved from the database
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation("Update a record in Customer Table")
    @PutMapping("/{customerId}")
    public ResponseEntity update(@PathVariable String customerId, @RequestBody Customer customer) throws Exception {
        if (customer.getStatus().equalsIgnoreCase("LEGACY") || customer.getStatus().equalsIgnoreCase("MIGRATED")) {
            customerService.updateTable(customerId, customer);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Deletes a row from the table if it exists
     * @param customerId Customer ID of the row to be deleted
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation("Deletes a record from Customer table")
    @DeleteMapping("/{customer_id}")
    public ResponseEntity delete(@PathVariable("customer_id") String customerId) throws Exception {
        customerService.deleteRow(customerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}