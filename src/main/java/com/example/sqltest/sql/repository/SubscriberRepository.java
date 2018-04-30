package com.example.sqltest.sql.repository;

import com.example.sqltest.sql.model.Subscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository which extends CrudRepository
 */
@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {
    /**
     * Finds a row with matching ServiceNum
     *
     * @param sNo is the serviceNum passed
     * @return a row with matching sNo
     */
    Subscriber findByServiceNum(String sNo);

    /**
     * Finds a list of rows with matching customerId
     * @param cId is the customerId passed
     * @return a list of rows with matching cId
     */
    List<Subscriber> findByCustomerId(String cId);
}
