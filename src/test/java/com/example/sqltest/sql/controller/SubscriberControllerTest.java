package com.example.sqltest.sql.controller;

import com.example.sqltest.sql.model.Subscriber;
import com.example.sqltest.sql.service.SubscriberServiceTest;
import com.example.sqltest.sql.service.serviceimpl.SubscriberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubscriberControllerTest {

    @Mock
    SubscriberServiceImpl subscriberServiceImpl;
    Subscriber setSubscriber;
    List<Subscriber> lisOfSubscribers;
    private MockMvc mockMvc;
    @InjectMocks
    private SubscriberController subscriberController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subscriberController)
                .build();
        setSubscriber = SubscriberServiceTest.setSubscriberRepository();    //Setting test values for Subscriber Object
        lisOfSubscribers = Arrays.asList(setSubscriber);    //Setting up a lisOfSubscribers of Subscriber values
        Mockito.when(subscriberServiceImpl.listTable()).thenReturn((List) lisOfSubscribers);    //Mock for getSubscribers
        Mockito.when(subscriberServiceImpl.listTableByCustomerId(Mockito.anyString())).thenReturn((List) lisOfSubscribers);     //Mock for getAllByCustomerId
        Mockito.when(subscriberServiceImpl.sNumExists(Mockito.anyString())).thenReturn(true);   //Mock to pass ServiceNum check
        Mockito.when(subscriberServiceImpl.sNumExists(Mockito.anyString())).thenReturn(false);  //Mock to fail ServiceNum check
        Mockito.doNothing().when(subscriberServiceImpl).create(setSubscriber);  //Mock for create
        Mockito.when(subscriberServiceImpl.updateTable(setSubscriber)).thenReturn(setSubscriber);   //Mock for update
        Mockito.doNothing().when(subscriberServiceImpl).deleteRow(Mockito.anyString());     //Mock for delete
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/db/subscriber/all"))
                .andExpect(status().isOk());
        assertEquals(subscriberController.getAll(), lisOfSubscribers);
    }

    @Test
    public void getAllByCustomerId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/db/subscriber/all/"))
                .andExpect(status().isOk());
        assertEquals(subscriberController.getAllByCustomerId(Mockito.anyString()), lisOfSubscribers);
    }

    @Test
    public void createRecordExists() throws Exception {
        Mockito.when(subscriberServiceImpl.sNumExists(Mockito.anyString())).thenReturn(true);
        mockMvc.perform(post("/db/subscriber/create"));
        assertEquals(200, subscriberController.create(setSubscriber).getStatusCodeValue());
    }

    @Test
    public void createRecordCreated() throws Exception {
        mockMvc.perform(post("/db/subscriber/create"));
        assertEquals(200, subscriberController.create(setSubscriber).getStatusCodeValue());
    }

    @Test
    public void createStatusCheck() throws Exception {
        setSubscriber.setStatus("sfdfsfsd");
        mockMvc.perform(post("/db/subscriber/create"));
        assertEquals(200, subscriberController.create(setSubscriber).getStatusCodeValue());
    }

    @Test
    public void updateNoSuchRecord() throws Exception {
        Mockito.when(subscriberServiceImpl.sNumExists(Mockito.anyString())).thenReturn(true);
        mockMvc.perform(post("/db/subscriber/update"));
        assertEquals(200, subscriberController.update(setSubscriber).getStatusCodeValue());
    }

    @Test
    public void updateRecordUpdated() throws Exception {
        mockMvc.perform(post("/db/subscriber/update"));
        assertEquals(200, subscriberController.update(setSubscriber).getStatusCodeValue());
    }

    @Test
    public void updateStatusCheck() throws Exception {
        Mockito.when(subscriberServiceImpl.sNumExists(Mockito.anyString())).thenReturn(true);
        setSubscriber.setStatus("fdggxdfg");
        mockMvc.perform(post("/db/subscriber/update"));
        assertEquals(200, subscriberController.update(setSubscriber).getStatusCodeValue());
    }

    @Test
    public void deleteIfNotExists() throws Exception {
        Mockito.when(subscriberServiceImpl.sNumExists(Mockito.anyString())).thenReturn(true);
        mockMvc.perform(post("/db/subscriber/delete/"));
        assertEquals(200, subscriberController.delete(setSubscriber.getCustomerId()).getStatusCodeValue());
    }


    @Test
    public void deleteIfExists() throws Exception {
        mockMvc.perform(post("/db/subscriber/delete/"));
        assertEquals(200, subscriberController.delete(Mockito.anyString()).getStatusCodeValue());
    }

}