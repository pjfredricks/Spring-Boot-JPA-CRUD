package com.example.sqltest.web;

import com.example.sqltest.repository.model.Subscriber;
import com.example.sqltest.service.SubscriberService;
import com.example.sqltest.service.serviceimpl.SubscriberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
@RunWith(SpringRunner.class)
public class SubscriberControllerTest {

    @Mock
    private SubscriberServiceImpl subscriberService;

    @InjectMocks
    private SubscriberController subscriberController;


    private Subscriber subscriber = new Subscriber();
    private List<Subscriber> subscribers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subscriber.setStatus("LEGACY");
        subscribers.add(subscriber);
    }

    @Test
    public void getSubscribers() throws Exception {
        given(subscriberService.getSubscribers()).willReturn(subscribers);
        assertEquals(200, subscriberController.getSubscribers().getStatusCodeValue());

        given(subscriberService.getSubscribers()).willReturn(new ArrayList<>());
        assertEquals(204, subscriberController.getSubscribers().getStatusCodeValue());
    }

    @Test
    public void getSubscriberByCustomerId() throws Exception {
        given(subscriberService.getSubscriberByCustomerId(anyString())).willReturn(subscribers);
        assertEquals(200, subscriberController.getSubscriberByCustomerId(anyString()).getStatusCodeValue());

        given(subscriberService.getSubscriberByCustomerId(anyString())).willReturn(new ArrayList<>());
        assertEquals(204, subscriberController.getSubscriberByCustomerId(anyString()).getStatusCodeValue());
    }

    @Test
    public void getSubscriberByServiceNum() throws Exception {
        given(subscriberService.getSubscriberByServiceNum(anyString())).willReturn(subscriber);
        assertEquals(200, subscriberController.getSubscriberByServiceNum(anyString()).getStatusCodeValue());

        given(subscriberService.getSubscriberByServiceNum(anyString())).willReturn(null);
        assertEquals(404, subscriberController.getSubscriberByServiceNum(anyString()).getStatusCodeValue());
    }

    @Test
    public void create() throws Exception {
        given(subscriberService.create(any(Subscriber.class))).willReturn(subscriber);
        assertEquals(201, subscriberController.create(subscriber).getStatusCodeValue());
    }

    @Test
    public void update() throws Exception {
        willDoNothing().given(subscriberService).update(any(Subscriber.class));
        assertEquals(202, subscriberController.update(subscriber).getStatusCodeValue());
    }

    @Test
    public void delete() throws Exception {
        willDoNothing().given(subscriberService).deleteRow(anyString());
        assertEquals(202, subscriberController.delete(anyString()).getStatusCodeValue());
    }

}