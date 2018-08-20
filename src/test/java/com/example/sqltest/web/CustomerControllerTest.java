package com.example.sqltest.web;

import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.service.serviceimpl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.mockito.BDDMockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerServiceImpl customerService;

    @InjectMocks
    CustomerController customerController;

    private Customer customer = new Customer();
    private List<Customer> customers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customer.setStatus("LEGACY");
        customers.add(customer);
    }

    @Test
    public void getCustomers() throws Exception {
        given(customerService.getCustomers()).willReturn(customers);
        assertEquals(200, customerController.getCustomers().getStatusCodeValue());

        given(customerService.getCustomers()).willReturn(customers);
        assertEquals(200, customerController.getCustomers().getStatusCodeValue());
    }

    @Test
    public void getCustomerByCustomerId() throws Exception {
        given(customerService.getCustomerByCustomerId(anyString())).willReturn(customer);
        assertEquals(200, customerController.getCustomerByCustomerId(anyString()).getStatusCodeValue());

        given(customerService.getCustomerByCustomerId(anyString())).willReturn(null);
        assertEquals(404, customerController.getCustomerByCustomerId(anyString()).getStatusCodeValue());
    }

    @Test
    public void create() throws Exception {
        given(customerService.create(any(Customer.class))).willReturn(customer);
        assertEquals(201, customerController.create(customer).getStatusCodeValue());

        customer.setStatus("MIGRATED");
        assertEquals(201, customerController.create(customer).getStatusCodeValue());
    }

    @Test
    public void update() throws Exception {
        willDoNothing().given(customerService).updateTable(any(Customer.class));
        assertEquals(201, customerController.update(customer).getStatusCodeValue());

        customer.setStatus("MIGRATED");
        assertEquals(201, customerController.update(customer).getStatusCodeValue());
    }

    @Test
    public void delete() throws Exception {
        willDoNothing().given(customerService).deleteRow(anyString());
        assertEquals(202, customerController.delete(anyString()).getStatusCodeValue());
    }

}