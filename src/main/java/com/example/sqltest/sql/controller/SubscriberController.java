package com.example.sqltest.sql.controller;

import com.example.sqltest.sql.exception.DbException;
import com.example.sqltest.sql.model.Subscriber;
import com.example.sqltest.sql.service.serviceimpl.SubscriberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.example.sqltest.sql.model.Response.*;

/**
 * Implements all CRUD Operations for customer table
 * @author Jfredricks
 * @version 1.0
 */
@RestController
@RequestMapping("db/subscriber")
public class SubscriberController {
    @Autowired
    private SubscriberServiceImpl subImpl;

    /**
     * Gets all rows from subscriber table
     * @return list of rows
     */
    @GetMapping("/all")
    public Iterable<Subscriber> getAll() {
        return subImpl.listTable();
    }

    /**
     * Get all rows with common customerId
     * @param cId is the customer ID provided by user
     * @return all subscriber rows with customer ID cId
     */
    @GetMapping("/all/{customer_id}")
    public List<Subscriber> getAllByCustomerId(@PathVariable("customer_id") String cId) {
        return subImpl.listTableByCustomerId(cId);
    }

    /**
     * Creates a new row if the values are validated successfully
     * @param s is the row passed as parameter
     * @return a response message
     * @throws DbException
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Subscriber s) throws DbException {
        if (s.getStatus().equals("LEGACY") || s.getStatus().equals("MIGRATED")) {
            if (subImpl.sNumExists(s.getServiceNum()))
                return commonResponse(RECORD_EXISTS);
        } else
            return commonResponse(STATUS_CHECK);
        subImpl.create(s);
        return commonResponse(RECORD_CREATED);
    }

    /**
     * Update an existing row in the table
     * @param s is the row passed as parameter
     * @return a response message
     * @throws DbException
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Subscriber s) throws DbException {
        if (!subImpl.sNumExists(s.getServiceNum()))
            return commonResponse(NO_SUCH_RECORD);
        else {
            if (s.getStatus().equals("LEGACY") || s.getStatus().equals("MIGRATED"))
                subImpl.updateTable(s);
            else
                return commonResponse(STATUS_CHECK);
            return commonResponse(RECORD_UPDATED);
        }
    }

    /**
     * Deletes a row from the table if it exists
     * @param sNo the ServiceNum of the table to be deleted
     * @return a response message
     * @throws DbException
     */
    @DeleteMapping("/delete/{service_num}")
    public ResponseEntity<?> delete(@PathVariable("service_num") String sNo) throws DbException {
        if (!subImpl.sNumExists(sNo))
            return commonResponse(NO_SUCH_RECORD);
        else {
            subImpl.deleteRow(sNo);
            return commonResponse(RECORD_DELETED);
        }
    }
}
