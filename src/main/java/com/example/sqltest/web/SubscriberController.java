package com.example.sqltest.web;

import com.example.sqltest.exception.DbException;
import com.example.sqltest.repository.model.Subscriber;
import com.example.sqltest.service.SubscriberService;
import com.example.sqltest.service.serviceimpl.SubscriberServiceImpl;
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
@RestController
@RequestMapping("db/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    /**
     * Gets all rows from subscriber table
     * @return list of rows
     */
    @ApiOperation("Retrieves all records from Subscriber table")
    @GetMapping("/all")
    public ResponseEntity<List<Subscriber>> getAll() {
        return new ResponseEntity<>(subscriberService.getSubscribers(), HttpStatus.OK);
    }

    /**
     * Get all rows with common customerId
     * @param customerId is the customer ID provided by user
     * @return all subscriber rows with customer ID cId
     */
    @ApiOperation("Retrieves all records from Subscriber table by Customer Id")
    @GetMapping("/all/{customerId}")
    public List<Subscriber> getSubscriberByCustomerId(@PathVariable("customerId") String customerId) {
        return subscriberService.getSubscriberByCustomerId(customerId);
    }

    /**
     * Creates a new row if the values are validated successfully
     * @param s is the row passed as parameter
     * @return a response message
     * @throws DbException
     */
   /* @ApiOperation("Adds a new record to Subscriber table")
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody Subscriber s) {
        if (s.getStatus().equals("LEGACY") || s.getStatus().equals("MIGRATED")) {
            if (subscriberService.sNumExists(s.getServiceNum()))
                return Response.commonResponse(Response.RECORD_EXISTS);
        } else
            return Response.commonResponse(Response.STATUS_CHECK);
        subscriberService.create(s);
        return Response.commonResponse(Response.RECORD_CREATED);
    }*/

    /**
     * Update an existing row in the table
     * @param s is the row passed as parameter
     * @return a response message
     * @throws DbException
     *//*
    @ApiOperation("Updates a record in Subscriber table")
    @PostMapping("/update")
    public ResponseEntity<Response> update(@RequestBody Subscriber s) {
        if (!subscriberService.sNumExists(s.getServiceNum()))
            return Response.commonResponse(Response.NO_SUCH_RECORD);
        else {
            if (s.getStatus().equals("LEGACY") || s.getStatus().equals("MIGRATED"))
                subscriberService.updateTable(s);
            else
                return Response.commonResponse(Response.STATUS_CHECK);
            return Response.commonResponse(Response.RECORD_UPDATED);
        }
    }

    *//**
     * Deletes a row from the table if it exists
     * @param sNo the ServiceNum of the table to be deleted
     * @return a response message
     * @throws DbException
     *//*
    @ApiOperation("Deletes a record from Subscriber table")
    @DeleteMapping("/delete/{service_num}")
    public ResponseEntity<Response> delete(@PathVariable("service_num") String sNo) {
        if (!subscriberService.sNumExists(sNo))
            return Response.commonResponse(Response.NO_SUCH_RECORD);
        else {
            subscriberService.deleteRow(sNo);
            return Response.commonResponse(Response.RECORD_DELETED);
        }
    }*/
}
