package com.example.sqltest.web;

import com.example.sqltest.service.CustomerService;
import com.example.sqltest.web.model.CustomerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sqltest.constants.SwaggerUIConstants.*;
import static com.example.sqltest.constants.URLConstants.*;

/**
 * Implements all CRUD Operations for customer table
 *
 * @author Jfredricks
 * @version 1.0
 */
@Api
@RestController
@RequestMapping(BASE_URL_CUSTOMER)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + CUSTOMER_TABLE)
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() throws Exception {
        List<CustomerDTO> customers = customerService.getCustomers();
        return !customers.isEmpty() ? new ResponseEntity<>(customers, HttpStatus.OK) : new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(RETRIEVES_ALL_RECORDS_FROM + CUSTOMER_TABLE + BY_CUSTOMER_ID)
    @GetMapping(CUSTOMER_ID)
    public ResponseEntity<CustomerDTO> getCustomerByCustomerId(@RequestParam String customerId) throws Exception {
        CustomerDTO customerDTO = customerService.getCustomerByCustomerId(customerId);
        return customerDTO != null ? new ResponseEntity<>(customerDTO, HttpStatus.OK) : new ResponseEntity<>(customerDTO, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(ADDS_A_NEW_RECORD_TO + CUSTOMER_TABLE)
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) throws Exception {
        if (customerDTO.getStatus().equalsIgnoreCase(LEGACY) || customerDTO.getStatus().equalsIgnoreCase(MIGRATED)) {
            return new ResponseEntity<>(customerService.create(customerDTO), HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    @ApiOperation(UPDATES_A_RECORD_IN + CUSTOMER_TABLE)
    @PutMapping
    public ResponseEntity update(@RequestBody CustomerDTO customerDTO) throws Exception {
        if (customerDTO.getStatus().equalsIgnoreCase(LEGACY) || customerDTO.getStatus().equalsIgnoreCase(MIGRATED)) {
            customerService.updateTable(customerDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new Exception("Invalid Status");
        }
    }

    @ApiOperation(DELETES_A_RECORD_FROM + CUSTOMER_TABLE)
    @DeleteMapping(CUSTOMER_ID)
    public ResponseEntity delete(@PathVariable("customer_id") String customerId) throws Exception {
        customerService.deleteRow(customerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}