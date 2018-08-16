package com.example.sqltest.controller;

import com.example.sqltest.model.Customer;
import com.example.sqltest.model.Response;
import com.example.sqltest.service.serviceimpl.CustomerServiceImpl;
import com.example.sqltest.exception.DbException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private CustomerServiceImpl cImpl;

    /**
     * Lists all rows in customer table
     * @return list of rows
     */
    @ApiOperation("Retrieves all record from Customer Table")
    @GetMapping("/all")
    public Iterable<Customer> getAll() {
        return cImpl.listTable();
    }

    /**
     * Creates a new row if the values are validated successfully
     * @param r is the row passed as parameter to this method
     * @return a response message
     * @throws DbException
     */
    @ApiOperation("Adds a new record to Customer table")
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody Customer r) {
        if (r.getStatus().equals("LEGACY") || r.getStatus().equals("MIGRATED")) {
            if (cImpl.recordExists(r.getCustomerId()))
                return Response.commonResponse(Response.RECORD_EXISTS);
        } else
            return Response.commonResponse(Response.STATUS_CHECK);
        cImpl.create(r);
        return Response.commonResponse(Response.RECORD_CREATED);
    }

    /**
     * Updates an existing row based on customer_id
     * @param r is the row retrieved from the database
     * @return a response message
     * @throws DbException
     */
    @ApiOperation("Update a record in Customer Table")
    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody Customer r) {
        if (!cImpl.recordExists(r.getCustomerId()))
            return Response.commonResponse(Response.NO_SUCH_RECORD);
        else {
            if (r.getStatus().equals("LEGACY") || r.getStatus().equals("MIGRATED"))
                cImpl.updateTable(r);
            else
                return Response.commonResponse(Response.STATUS_CHECK);
            return Response.commonResponse(Response.RECORD_UPDATED);
        }
    }

    /**
     * Deletes a row from the table if it exists
     * @param cId Customer ID of the row to be deleted
     * @return a response message
     * @throws DbException
     */
    @ApiOperation("Deletes a record from Customer table")
    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<Response> delete(@PathVariable("customer_id") String cId) {
        if (!cImpl.recordExists(cId))
            return Response.commonResponse(Response.NO_SUCH_RECORD);
        else {
            cImpl.deleteRow(cId);
            return Response.commonResponse(Response.RECORD_DELETED);
        }
    }
}