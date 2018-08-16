package com.example.sqltest.controller;

import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.repository.model.Response;
import com.example.sqltest.service.serviceimpl.CustomerServiceImpl;
import com.example.sqltest.exception.DbException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Implements all CRUD Operations for customer table
 * @author Jfredricks
 * @version 1.0
 */
@Api
@RestController
@RequestMapping("db/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    /**
     * Lists all rows in customer table
     * @return list of rows
     */
    @ApiOperation("Retrieves all record from Customer Table")
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.listTable(), HttpStatus.OK);
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<Customer> getCustomerByCustomerId(@RequestParam String customerId) {
        return new ResponseEntity<>(customerService.getCustomerByCustomerId(customerId), HttpStatus.OK);
    }

    /**
     * Creates a new row if the values are validated successfully
     * @param customer is the row passed as parameter to this method
     * @return a response message
     * @throws DbException
     */
    @ApiOperation("Adds a new record to Customer table")
    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        if (customer.getStatus().equals("LEGACY") || customer.getStatus().equals("MIGRATED")) {
            if (!customerService.recordExists(customer.getCustomerId()))
                return new ResponseEntity<>(customerService.updateTable(customer));
        } else {
            return new ResponseEntity<>(customerService.create(customer));
        }
    }

    /**
     * Updates an existing row based on customer_id
     * @param customer is the row retrieved from the database
     * @return a response message
     * @throws DbException
     */
    @ApiOperation("Update a record in Customer Table")
    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody Customer customer) {
        if (!customerService.recordExists(customer.getCustomerId()))
            return Response.commonResponse(Response.NO_SUCH_RECORD);
        else {
            if (customer.getStatus().equals("LEGACY") || customer.getStatus().equals("MIGRATED"))
                customerService.updateTable(customer);
            else
                return Response.commonResponse(Response.STATUS_CHECK);
            return Response.commonResponse(Response.RECORD_UPDATED);
        }
    }

    /**
     * Deletes a row from the table if it exists
     * @param customerId Customer ID of the row to be deleted
     * @return a response message
     * @throws DbException
     */
    @ApiOperation("Deletes a record from Customer table")
    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<Response> delete(@PathVariable("customer_id") String customerId) {
        if (!customerService.recordExists(customerId))
            return Response.commonResponse(Response.NO_SUCH_RECORD);
        else {
            customerService.deleteRow(customerId);
            return Response.commonResponse(Response.RECORD_DELETED);
        }
    }
}