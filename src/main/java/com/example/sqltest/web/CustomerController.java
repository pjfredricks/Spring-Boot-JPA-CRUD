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

import static com.example.sqltest.constants.SwaggerUIConstants.*;
import static com.example.sqltest.constants.URLConstants.*;

/**
 * Implements all CRUD Operations for customer table
 *
 * @author Jfredricks
 * @version 1.0
 */
@Api
@RestController
@RequestMapping(BASE_URL)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Lists all rows in customer table
     *
     * @return ResponseEntity
     */
    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + CUSTOMER_TABLE)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() throws Exception {
        List<Customer> customers = customerService.getCustomers();
        return !customers.isEmpty() ? new ResponseEntity<>(customers, HttpStatus.OK) : new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a record from Customer table by CustomerId
     *
     * @param customerId
     * @return ResponseEntity
     * @throws Exception
     */
    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + CUSTOMER_TABLE + BY_CUSTOMER_ID)
    @GetMapping(CUSTOMER_ID)
    public ResponseEntity<Customer> getCustomerByCustomerId(@RequestParam String customerId) throws Exception {
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK) : new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a new row if the values are validated successfully
     *
     * @param customer is the row passed as parameter to this method
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation(ADDS_A_NEW_RECORD_TO + CUSTOMER_TABLE)
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) throws Exception {
        if (customer.getStatus().equalsIgnoreCase(LEGACY) || customer.getStatus().equalsIgnoreCase(MIGRATED)) {
            return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Updates an existing row based on customer_id
     *
     * @param customer is the row retrieved from the database
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation(UPDATES_A_RECORD_IN + CUSTOMER_TABLE)
    @PutMapping
    public ResponseEntity update(@RequestBody Customer customer) throws Exception {
        if (customer.getStatus().equalsIgnoreCase(LEGACY) || customer.getStatus().equalsIgnoreCase(MIGRATED)) {
            customerService.updateTable(customer);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Deletes a row from the table if it exists
     *
     * @param customerId Customer ID of the row to be deleted
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation(DELETES_A_RECORD_FROM + CUSTOMER_TABLE)
    @DeleteMapping(CUSTOMER_ID)
    public ResponseEntity delete(@PathVariable("customer_id") String customerId) throws Exception {
        customerService.deleteRow(customerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}