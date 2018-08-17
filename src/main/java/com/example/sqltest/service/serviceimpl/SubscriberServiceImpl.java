package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.repository.SubscriberRepository;
import com.example.sqltest.repository.model.Subscriber;
import com.example.sqltest.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Implementation of SubscriberService
 *
 * @author Jfredricks
 * @version 1.0
 * @see SubscriberService
 */
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    /**
     * @return List of all tables in Subscriber
     */
    public List<Subscriber> getSubscribers() throws Exception{
        return subscriberRepository.getSubscribers();
    }

    @Override
    public void update(Subscriber subscriber) throws Exception {
        if(!sNumExists(subscriber.getServiceNum())){
            throw new Exception("Record does not Exist");
        }
        subscriberRepository.update(subscriber);
    }

    @Override
    public Subscriber getSubscriberByServiceNum(String serviceNum) throws Exception{
        return subscriberRepository.getSubscriberByServiceNum(serviceNum);
    }

    /**
     * Saves the parameters passed as a row in subscriber table
     *
     * @param subscriber is saved
     */
    public Subscriber create(@RequestBody Subscriber subscriber)  throws Exception{
        if(!sNumExists(subscriber.getServiceNum())) {
            throw new Exception("Record already Exists");
        }
        subscriberRepository.save(subscriber);
        return subscriber;
    }

    /**
     * Checks if a record exists
     *
     * @param serviceNum is the ServiceNum of the record
     * @return true or false
     */
    private boolean sNumExists(String serviceNum) throws Exception{
        return subscriberRepository.getSubscriberByServiceNum(serviceNum) != null;
    }

    /**
     * Deletes a row from subscriber table
     * @param serviceNum is the ServiceNum of the row to be deleted
     */
    public void deleteRow(String serviceNum) throws Exception{
        Subscriber foundByServiceNum = subscriberRepository.getSubscriberByServiceNum(serviceNum);
        subscriberRepository.delete(foundByServiceNum);
    }

    /**
     * Lists all rows in Subscriber table with common Customer Id
     *
     * @param customerId is the Customer Id
     * @return rows with common cId
     */
    public List<Subscriber> getSubscriberByCustomerId(String customerId) throws Exception{
        return subscriberRepository.getSubscriberByCustomerId(customerId);
    }
}