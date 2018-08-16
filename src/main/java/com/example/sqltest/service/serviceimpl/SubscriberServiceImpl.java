package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.model.Subscriber;
import com.example.sqltest.repository.SubscriberRepository;
import com.example.sqltest.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Implementation of SubscriberService
 *
 * @author Jfredricks
 * @version 1.0
 * @see SubscriberService
 */
@org.springframework.stereotype.Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository sRepo;

    /**
     * @return List of all tables in Subscriber
     */
    public Iterable<Subscriber> listTable() {
        return sRepo.findAll();
    }

    /**
     * Saves the parameters passed as a row in subscriber table
     * @param subscriber is saved
     */
    public void create(@RequestBody Subscriber subscriber) {
        sRepo.save(subscriber);
    }

    /**
     * Checks if a record exists
     * @param sNum is the ServiceNum of the record
     * @return true or false
     */
    public boolean sNumExists(String sNum) {
        return sRepo.findByServiceNum(sNum) != null;
    }

    /**
     * Updates the subscriber table row
     * @param subscriber is the values to be updated
     * @return saved values
     */
    public Subscriber updateTable(@RequestBody Subscriber subscriber) {
        Subscriber foundByServiceNum = sRepo.findByServiceNum(subscriber.getServiceNum());
        foundByServiceNum.setAccountNum(subscriber.getAccountNum());
        foundByServiceNum.setCreatedBy(subscriber.getCreatedBy());
        foundByServiceNum.setStatus(subscriber.getStatus());
        return sRepo.save(foundByServiceNum);
    }

    /**
     * Deletes a row from subscriber table
     * @param sNo is the ServiceNum of the row to be deleted
     */
    public void deleteRow(String sNo) {
        Subscriber foundByServiceNum = sRepo.findByServiceNum(sNo);
        sRepo.delete(foundByServiceNum);
    }

    /**
     * Lists all rows in Subscriber table with common Customer Id
     * @param cId is the Customer Id
     * @return rows with common cId
     */
    public List<Subscriber> listTableByCustomerId(String cId) {
        return sRepo.findByCustomerId(cId);
    }

}