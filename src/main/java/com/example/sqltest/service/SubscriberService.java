package com.example.sqltest.service;

import com.example.sqltest.repository.model.Subscriber;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Defines the various operations performed on subscriber table
 *
 * @author Jfredricks
 * @version 1.0
 * @see Subscriber
 */
public interface SubscriberService {
    /**
     * Lists out all rows in customer table
     */
    List<Subscriber> getSubscribers();

    /**
     * Creates a new row in subscriber table
     *
     * @param subscriber is the object with values to be updated
     *//*
    void create(@RequestBody Subscriber subscriber);

    *//**
     * Check if a row exists or not
     * @param sNum is the serviceNum to be checked
     * @return true or false
     *//*
    boolean sNumExists(String sNum);

    *//**
     * Updates a row with new values
     * @param subscriber is the object with values to be updated
     *//*
    Subscriber updateTable(@RequestBody Subscriber subscriber);

    *//**
     * deletes a row from subscriber table
     * @param sNo is the serviceNum of the row to be deleted
     *//*
    void deleteRow(String sNo);

    *//**
     * Lists all rows in subscriber table with common customerId
     * @param customerId is the custometId to be checked
     * @return a list of subscriber rows
     */
    List<Subscriber> getSubscriberByCustomerId(String customerId);
}