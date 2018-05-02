package com.example.sqltest.sql.controller;

import com.example.sqltest.sql.exception.DbException;
import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.model.Response;
import com.example.sqltest.sql.service.serviceimpl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.example.sqltest.sql.model.Response.*;

/**
 * Implements all CRUD Operations for customer table
 * @author Jfredricks
 * @version 1.0
 */
@RestController
@RequestMapping("db/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl cImpl;

    /**
     * Lists all rows in customer table
     * @return list of rows
     */
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
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody Customer r) {
        if (r.getStatus().equals("LEGACY") || r.getStatus().equals("MIGRATED")) {
            if (cImpl.recordExists(r.getCustomerId()))
                return commonResponse(RECORD_EXISTS);
        } else
            return commonResponse(STATUS_CHECK);
        cImpl.create(r);
        return commonResponse(RECORD_CREATED);
    }

    /**
     * Updates an existing row based on customer_id
     * @param r is the row retrieved from the database
     * @return a response message
     * @throws DbException
     */
    @PostMapping("/update")
    public ResponseEntity<Response> update(@RequestBody Customer r) {
        if (!cImpl.recordExists(r.getCustomerId()))
            return commonResponse(NO_SUCH_RECORD);
        else {
            if (r.getStatus().equals("LEGACY") || r.getStatus().equals("MIGRATED"))
                cImpl.updateTable(r);
            else
                return commonResponse(STATUS_CHECK);
            return commonResponse(RECORD_UPDATED);
        }
    }

    /**
     * Deletes a row from the table if it exists
     * @param cId Customer ID of the row to be deleted
     * @return a response message
     * @throws DbException
     */
    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<Response> delete(@PathVariable("customer_id") String cId) {
        if (!cImpl.recordExists(cId))
            return commonResponse(NO_SUCH_RECORD);
        else {
            cImpl.deleteRow(cId);
            return commonResponse(RECORD_DELETED);
        }
    }
}