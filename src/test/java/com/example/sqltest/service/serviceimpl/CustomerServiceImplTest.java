package com.example.sqltest.service.serviceimpl;

import com.example.sqltest.repository.CustomerRepository;
import com.example.sqltest.repository.impl.CustomerRepositoryImpl;
import com.example.sqltest.repository.model.Customer;
import com.example.sqltest.service.CustomerService;
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
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    Customer customer = new Customer();
    List<Customer> customers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customer.setCustomerId("120");
    }

    @Test
    public void create() throws Exception {
        willDoNothing().given(customerRepository).save(any(Customer.class));
        assertNotNull(customerService.create(customer));
    }

    @Test
    public void getCustomers() throws Exception {
        given(customerRepository.getCustomers()).willReturn(customers);
        assertNotNull(customerRepository.getCustomers());
    }

    @Test
    public void getCustomerByCustomerId() throws Exception {
        given(customerRepository.getCustomerByCustomerId(anyString())).willReturn(customer);
        assertNotNull(customerService.getCustomerByCustomerId(anyString()));
    }

    @Test
    public void updateTable() throws Exception {
        given(customerRepository.getCustomerByCustomerId(anyString())).willReturn(customer);
        customer.setStatus("LEGACY");
        customer.setAccountNum("1001");
        customer.setCreatedBy("admin");
        willDoNothing().given(customerRepository).update(customer);
        customerService.updateTable(customer);
    }

    @Test
    public void deleteRow() throws Exception {
        given(customerRepository.getCustomerByCustomerId(anyString())).willReturn(customer);
        customerService.deleteRow(anyString());
    }

}