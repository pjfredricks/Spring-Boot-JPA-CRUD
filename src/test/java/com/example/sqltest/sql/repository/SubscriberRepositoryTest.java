package com.example.sqltest.sql.repository;

import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.model.Subscriber;
import com.example.sqltest.sql.service.CustomerServiceTest;
import com.example.sqltest.sql.service.SubscriberServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class SubscriberRepositoryTest {

    Customer setCustomer;
    Subscriber setSubscriber, savedSubscribers, foundSubscribers;
    List<Customer> customerList;
    List<Subscriber> subscriberList, subscriberListByCId;
    @Autowired
    @MockBean(name = "SubscriberRepository")
    private SubscriberRepository subscriberRepository;
    @Autowired
    @MockBean(name = "CustomerRepository")
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
        setCustomer = CustomerServiceTest.setCustomerRepository();
        setSubscriber = SubscriberServiceTest.setSubscriberRepository();
        customerList = new ArrayList<>();
        subscriberList = new ArrayList<>();
    }


    @Test
    public void testFindByServiceNum() {
        customerRepository.save(setCustomer);
        savedSubscribers = subscriberRepository.save(setSubscriber);
        foundSubscribers = subscriberRepository.findByServiceNum(setSubscriber.getServiceNum());
        assertEquals(foundSubscribers.getCustomerId(), savedSubscribers.getCustomerId());
    }

    @Test
    public void testFindByCustomerId() {
        savedSubscribers = subscriberRepository.save(setSubscriber);
        customerList.add(setCustomer);
        subscriberList.add(savedSubscribers);
        subscriberListByCId = subscriberRepository.findByCustomerId(savedSubscribers.getCustomerId());
        assertEquals(subscriberListByCId.isEmpty(), subscriberList.isEmpty());
    }
}