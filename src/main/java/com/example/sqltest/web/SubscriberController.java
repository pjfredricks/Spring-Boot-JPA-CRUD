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

import static com.example.sqltest.constants.SwaggerUIConstants.*;
import static com.example.sqltest.constants.URLConstants.*;

/**
 * Implements all CRUD Operations for customer table
 *
 * @author Jfredricks
 * @version 1.0
 */
@RestController
@RequestMapping(BASE_URL_SUBSCRIBER)
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    /**
     * Gets all rows from subscriber table
     *
     * @return ResponseEntity
     */
    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + SUBSCRIBER_TABLE)
    @GetMapping
    public ResponseEntity<List<Subscriber>> getSubscribers() throws Exception {
        List<Subscriber> subscribers = subscriberService.getSubscribers();
        return !subscribers.isEmpty() ? new ResponseEntity<>(subscribers, HttpStatus.OK) : new ResponseEntity<>(subscribers, HttpStatus.NO_CONTENT);
    }

    /**
     * Get all rows with common customerId
     *
     * @param customerId is the customer ID provided by user
     * @return ResponseEntity
     */
    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + SUBSCRIBER_TABLE + BY_CUSTOMER_ID)
    @GetMapping(CUSTOMER_ID)
    public ResponseEntity<List<Subscriber>> getSubscriberByCustomerId(@PathVariable("customerId") String customerId) throws Exception {
        List<Subscriber> subscribers = subscriberService.getSubscriberByCustomerId(customerId);
        return !subscribers.isEmpty() ? new ResponseEntity<>(subscribers, HttpStatus.OK) : new ResponseEntity<>(subscribers, HttpStatus.NO_CONTENT);
    }


    /**
     * Gets Subscriber record by Service Num
     *
     * @param serviceNum
     * @return
     * @throws Exception
     */
    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + SUBSCRIBER_TABLE + BY_SERVICE_NUM)
    @GetMapping(SERVICE_NUM)
    public ResponseEntity<Subscriber> getSubscriberByServiceNum(@PathVariable("serviceNum") String serviceNum) throws Exception {
        Subscriber subscriber = subscriberService.getSubscriberByServiceNum(serviceNum);
        return subscriber != null ? new ResponseEntity<>(subscriber, HttpStatus.OK) : new ResponseEntity<>(subscriber, HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a new row if the values are validated successfully
     *
     * @param subscriber is the row passed as parameter
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation(ADDS_A_NEW_RECORD_TO + SUBSCRIBER_TABLE)
    @PostMapping
    public ResponseEntity<Subscriber> create(@RequestBody Subscriber subscriber) throws Exception {
        if (subscriber.getStatus().equalsIgnoreCase(LEGACY) || subscriber.getStatus().equalsIgnoreCase(MIGRATED)) {
            return new ResponseEntity<>(subscriberService.create(subscriber), HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    /**
     * Update an existing row in the table
     *
     * @param subscriber is the row passed as parameter
     * @return ResponseEntity
     * @throws DbException
     */
    @ApiOperation(UPDATES_A_RECORD_IN + SUBSCRIBER_TABLE)
    @PutMapping
    public ResponseEntity update(@RequestBody Subscriber subscriber) throws Exception {
        if (subscriber.getStatus().equalsIgnoreCase(LEGACY) || subscriber.getStatus().equalsIgnoreCase(MIGRATED)) {
            subscriberService.update(subscriber);
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
    @ApiOperation(DELETES_A_RECORD_FROM + SUBSCRIBER_TABLE)
    @DeleteMapping(SERVICE_NUM)
    public ResponseEntity delete(@PathVariable("serviceNum") String serviceNum) throws Exception {
        subscriberService.deleteRow(serviceNum);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
