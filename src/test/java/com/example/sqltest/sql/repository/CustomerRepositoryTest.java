package com.example.sqltest.sql.repository;

import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.service.CustomerServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    Customer setCustomer, savedCustomer, foundCustomer;
    @Autowired
    @MockBean(name = "CustomerRepository")
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
        setCustomer = CustomerServiceTest.setCustomerRepository();
        savedCustomer = customerRepository.save(setCustomer);
    }

    @Test
    public void findByCustomerId() {
        foundCustomer = customerRepository.findByCustomerId(setCustomer.getCustomerId());
        assertEquals(foundCustomer.getCustomerId(), savedCustomer.getCustomerId());
    }
}