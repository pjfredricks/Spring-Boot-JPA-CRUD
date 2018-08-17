package com.example.sqltest.web;

import com.example.sqltest.exception.DbException;
import com.example.sqltest.repository.model.Subscriber;
import com.example.sqltest.service.SubscriberService;
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
@RestController
@RequestMapping("db/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    /**
     * Gets all rows from subscriber table
     *
     * @return ResponseEntity
     */
    @ApiOperation("Retrieves all records from Subscriber table")
    @GetMapping("")
    public ResponseEntity<List<Subscriber>> getSubscribers() throws Exception {
        return new ResponseEntity<>(subscriberService.getSubscribers(), HttpStatus.OK);
    }

    /**
     * Get all rows with common customerId
     *
     * @param customerId is the customer ID provided by user
     * @return ResponseEntity
     */
    @ApiOperation("Retrieves all records from Subscriber table by Customer Id")
    @GetMapping("/{customerId}")
    public List<Subscriber> getSubscriberByCustomerId(@PathVariable("customerId") String customerId) throws Exception {
        return subscriberService.getSubscriberByCustomerId(customerId);
    }


    /**
     * Gets
     *
     * @param serviceNum
     * @return
     * @throws Exception
     */
    @ApiOperation("Retrieves all records from Subscriber table by Customer Id")
    @GetMapping("/{serviceNum}")
    public Subscriber getSubscriberByServiceNum(@PathVariable("serviceNum") String serviceNum) throws Exception {
        return subscriberService.getSubscriberByServiceNum(serviceNum);
    }

    /**
     * Creates a new row if the values are validated successfully
     *
     * @param subscriber is the row passed as parameter
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation("Adds a new record to Subscriber table")
    @PostMapping("")
    public ResponseEntity<Subscriber> create(@RequestBody Subscriber subscriber) throws Exception {
        if (subscriber.getStatus().equalsIgnoreCase("LEGACY") || subscriber.getStatus().equalsIgnoreCase("MIGRATED")) {
            return new ResponseEntity<>(subscriberService.create(subscriber), HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Update an existing row in the table
     *
     * @param s is the row passed as parameter
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation("Updates a record in Subscriber table")
    @PostMapping("")
    public ResponseEntity update(@RequestBody Subscriber s) throws Exception {
        if (s.getStatus().equalsIgnoreCase("LEGACY") || s.getStatus().equalsIgnoreCase("MIGRATED")) {
            subscriberService.update(s);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Deletes a row from the table if it exists
     *
     * @param serviceNum the ServiceNum of the table to be deleted
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation("Deletes a record from Subscriber table")
    @DeleteMapping("/delete/{service_num}")
    public ResponseEntity delete(@PathVariable("serviceNum") String serviceNum) throws Exception {
        subscriberService.deleteRow(serviceNum);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
