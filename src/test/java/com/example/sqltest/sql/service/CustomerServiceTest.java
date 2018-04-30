package com.example.sqltest.sql.service;

import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.repository.CustomerRepository;
import com.example.sqltest.sql.service.serviceimpl.CustomerServiceImpl;
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
public class CustomerServiceTest {

    Customer setCustomer;
    List<Customer> list;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    public static Customer setCustomerRepository() {
        Customer customer = new Customer();
        customer.setRid(1);
        customer.setCreatedBy("1");
        customer.setAccountNum("1");
        customer.setStatus("LEGACY");
        customer.setCustomerId("1");
        return customer;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        setCustomer = setCustomerRepository();
        list = new ArrayList<>();
        Mockito.when(customerRepository.save(setCustomer)).thenReturn(setCustomer);     //Mock for save
        Mockito.when(customerRepository.findByCustomerId(anyString())).thenReturn(setCustomer);     //Mock for finding by Customer ID
        Mockito.doNothing().when(customerRepository).delete(setCustomer);       //Mock for delete
    }

    @Test
    public void testCreate() throws Exception {
        customerServiceImpl.create(setCustomer);
    }

    @Test
    public void testListTable() throws Exception {
        list.add(setCustomer);
        Mockito.when(customerRepository.findAll()).thenReturn(list);
        Iterable<Customer> result = customerServiceImpl.listTable();
        assertEquals(setCustomer.getCreatedBy(), result.iterator().next().getCreatedBy());
        assertEquals(setCustomer.getRid(), result.iterator().next().getRid());
        assertEquals(setCustomer.getAccountNum(), result.iterator().next().getAccountNum());
        assertEquals(setCustomer.getStatus(), result.iterator().next().getStatus());
        assertEquals(setCustomer.getCustomerId(), result.iterator().next().getCustomerId());
    }

    @Test
    public void testUpdateTable() throws Exception {
        setCustomer.setAccountNum("123");
        setCustomer.setCreatedBy("123");
        setCustomer.setStatus("MIGRATED");
        Mockito.when(customerRepository.save(setCustomer)).thenReturn(setCustomer);
        Customer c = customerServiceImpl.updateTable(setCustomer);
        assertEquals(setCustomer.getStatus(), c.getStatus());
        assertEquals(setCustomer.getCreatedBy(), c.getCreatedBy());
        assertEquals(setCustomer.getAccountNum(), c.getAccountNum());

    }

    @Test
    public void testDeleteRow() throws Exception {
        customerServiceImpl.deleteRow(Mockito.anyString());
    }

    @Test
    public void recordExists() {
        assertEquals(true, customerServiceImpl.recordExists(anyString()));
    }

}