package com.example.sqltest.repository;

import com.example.sqltest.repository.model.Subscriber;

import java.util.List;

/**
 * Repository which extends CrudRepository
 */

public interface SubscriberRepository extends CommonRepository {

    List<Subscriber> getSubscribers() throws Exception;

    List<Subscriber> getSubscriberByCustomerId(String customerId) throws Exception;

    Subscriber getSubscriberByServiceNum(String serviceNum) throws Exception;
}
