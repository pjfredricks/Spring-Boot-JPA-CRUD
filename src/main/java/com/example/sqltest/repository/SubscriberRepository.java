package com.example.sqltest.repository;

import com.example.sqltest.repository.model.Subscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository which extends CrudRepository
 */

public interface SubscriberRepository {


    List<Subscriber> getSubscribers()  throws Exception;

    List<Subscriber> getSubscriberByCustomerId(String customerId)  throws Exception;

     Subscriber getSubscriberByServiceNum(String serviceNum)  throws Exception;

     void save(Subscriber subscriber)  throws Exception;

     void update(Subscriber subscriber) throws Exception;

     void delete(Subscriber subscriber) throws Exception;
}
