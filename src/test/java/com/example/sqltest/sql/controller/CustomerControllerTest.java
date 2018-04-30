package com.example.sqltest.sql.controller;

import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.service.CustomerServiceTest;
import com.example.sqltest.sql.service.serviceimpl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerServiceImpl customerServiceImpl;
    Customer setCustomer;
    List<Customer> listOfCustomers;
    private MockMvc mockMvc;
    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();

        setCustomer = CustomerServiceTest.setCustomerRepository();     //Setting test values for Customer Object
        listOfCustomers = Arrays.asList(setCustomer);      //Setting up a lisOfSubscribers of Customer values
        Mockito.when(customerServiceImpl.listTable()).thenReturn((List) listOfCustomers);   //Mock for getAll
        Mockito.when(customerServiceImpl.recordExists(Mockito.anyString())).thenReturn(true);
        Mockito.when(customerServiceImpl.recordExists(Mockito.anyString())).thenReturn(false); //Mock for checking record exists
        Mockito.doNothing().when(customerServiceImpl).create(setCustomer);  //Mock for create
        Mockito.when(customerServiceImpl.updateTable(setCustomer)).thenReturn(setCustomer); //Mock for update
        Mockito.doNothing().when(customerServiceImpl).deleteRow(Mockito.anyString());   //Mock for delete
    }


    @Test
    public void getAll() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/db/customer/all"))
                .andExpect(status().isOk());
        assertEquals(customerController.getAll(), listOfCustomers);
    }

    @Test
    public void createRecordExists() throws Exception {
        mockMvc.perform(post("/db/customer/create"));
        assertEquals(HttpStatus.OK, customerController.create(setCustomer).getStatusCode());
    }

    @Test
    public void createRecordCreated() throws Exception {
        mockMvc.perform(post("/db/customer/create"));
        assertEquals(HttpStatus.OK, customerController.create(setCustomer).getStatusCode());
    }

    @Test
    public void createStatusCheck() throws Exception {
        setCustomer.setStatus("sdffsdfs");
        mockMvc.perform(post("/db/customer/create"));
        assertEquals(HttpStatus.OK, customerController.create(setCustomer).getStatusCode());

    }

    @Test
    public void updateNoSuchRecord() throws Exception {
        mockMvc.perform(post("/db/customer/update"));
        assertEquals(HttpStatus.OK, customerController.update(setCustomer).getStatusCode());
    }

    @Test
    public void updateRecordUpdated() throws Exception {
        mockMvc.perform(post("/db/customer/update"));
        assertEquals(HttpStatus.OK, customerController.update(setCustomer).getStatusCode());
    }

    @Test
    public void updateStatusCheck() throws Exception {
        setCustomer.setStatus("dsffsdfs");
        mockMvc.perform(post("/db/customer/update"));
        assertEquals(200, customerController.update(setCustomer).getStatusCodeValue());
    }

    @Test
    public void deleteIfExists() throws Exception {
        mockMvc.perform(post("/db/customer/delete/{customer_id}", "123"));
        assertEquals(200, customerController.delete(setCustomer.getCustomerId()).getStatusCodeValue());
    }

    @Test
    public void deleteIfNotExists() throws Exception {
        mockMvc.perform(post("/db/customer/delete/{customer_id}", "1212"));
        assertEquals(200, customerController.delete(Mockito.anyString()).getStatusCodeValue());
    }

}