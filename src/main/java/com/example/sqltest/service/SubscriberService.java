package com.example.sqltest.service;

import com.example.sqltest.repository.model.Subscriber;

import java.util.List;

/**
 * Defines the various operations performed on subscriber table
 *
 * @author Jfredricks
 * @version 1.0
 * @see Subscriber
 */
public interface SubscriberService {

    List<Subscriber> getSubscribers() throws Exception;

    Subscriber getSubscriberByServiceNum(String serviceNum) throws Exception;

    Subscriber create(Subscriber subscriber) throws Exception;

    void update(Subscriber subscriber) throws Exception;

    void deleteRow(String sNo)  throws Exception;

    List<Subscriber> getSubscriberByCustomerId(String customerId) throws Exception;
}