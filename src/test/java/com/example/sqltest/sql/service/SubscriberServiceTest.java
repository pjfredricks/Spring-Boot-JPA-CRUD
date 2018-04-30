package com.example.sqltest.sql.service;

import com.example.sqltest.sql.model.Subscriber;
import com.example.sqltest.sql.repository.SubscriberRepository;
import com.example.sqltest.sql.service.serviceimpl.SubscriberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
public class SubscriberServiceTest {
    Subscriber setSubscriber;
    List<Subscriber> list;
    Iterable<Subscriber> subscriberIterable;
    @Mock
    private SubscriberRepository subscriberRepository;
    @InjectMocks
    private SubscriberServiceImpl subscriberServiceImpl;

    public static Subscriber setSubscriberRepository() {
        Subscriber subscriber = new Subscriber();
        subscriber.setRid(1);
        subscriber.setCreatedBy("1");
        subscriber.setAccountNum("1");
        subscriber.setStatus("LEGACY");
        subscriber.setCustomerId("1");
        subscriber.setServiceNum("1");
        return subscriber;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        setSubscriber = setSubscriberRepository();
        list = new ArrayList<>();

        Mockito.when(subscriberRepository.findByCustomerId(anyString())).thenReturn(list);  //Mock to find list of Subscribers with common Customer ID
        Mockito.when(subscriberRepository.save(setSubscriber)).thenReturn(setSubscriber);   //Mock for save
        Mockito.when(subscriberRepository.findAll()).thenReturn(list);   //Mock for list
        Mockito.when(subscriberRepository.findByServiceNum(anyString())).thenReturn(setSubscriber); //Mock for finding by Service Number
        Mockito.doNothing().when(subscriberRepository).delete(setSubscriber);   //Mock for delete


    }

    @Test
    public void testListTable() throws Exception {
        list.add(setSubscriber);
        subscriberIterable = subscriberServiceImpl.listTable();
        assertEquals(setSubscriber.getCreatedBy(), subscriberIterable.iterator().next().getCreatedBy());
        assertEquals(setSubscriber.getRid(), subscriberIterable.iterator().next().getRid());
        assertEquals(setSubscriber.getAccountNum(), subscriberIterable.iterator().next().getAccountNum());
        assertEquals(setSubscriber.getStatus(), subscriberIterable.iterator().next().getStatus());
        assertEquals(setSubscriber.getCustomerId(), subscriberIterable.iterator().next().getCustomerId());
        assertEquals(setSubscriber.getServiceNum(), subscriberIterable.iterator().next().getServiceNum());
    }

    @Test
    public void testListTableByCustomerId() throws Exception {
        assertEquals(subscriberServiceImpl.listTableByCustomerId(anyString()), list);
    }


    @Test
    public void testCreate() {
        subscriberServiceImpl.create(setSubscriber);
    }

    @Test
    public void testSNumExists() {
        assertEquals(true, subscriberServiceImpl.sNumExists(anyString()));
    }

    @Test
    public void testUpdateTable() {
        setSubscriber = setSubscriberRepository();
        setSubscriber.setAccountNum("123");
        setSubscriber.setCreatedBy("123");
        setSubscriber.setStatus("MIGRATED");
        Subscriber updatedSubscriber = subscriberServiceImpl.updateTable(setSubscriber);
        assertEquals(updatedSubscriber.getStatus(), setSubscriber.getStatus());
        assertEquals(updatedSubscriber.getCreatedBy(), setSubscriber.getCreatedBy());
        assertEquals(updatedSubscriber.getAccountNum(), setSubscriber.getAccountNum());
    }

    @Test
    public void testDeleteRow() {
        subscriberServiceImpl.deleteRow(anyString());
    }
}