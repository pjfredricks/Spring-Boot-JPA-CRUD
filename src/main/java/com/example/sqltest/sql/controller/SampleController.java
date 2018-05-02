package com.example.sqltest.sql.controller;

import com.example.sqltest.sql.model.Customer;
import com.example.sqltest.sql.model.Sample;
import com.example.sqltest.sql.service.serviceimpl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import static com.fasterxml.jackson.databind.node.JsonNodeType.POJO;


@RestController
@RequestMapping("db/sample")
public class SampleController {

    @Autowired
    CustomerServiceImpl customerService = new CustomerServiceImpl();


    /**
     * Displays values the user has entered as json
     */
    @PostMapping(value = "/display")
    public ResponseEntity<String> displayEntered(@RequestBody Customer customer) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //convert object to json
        // objectMapper.writeValue(new File("E:\\sample.json"),customer);
        //Store json in string
        String sampleJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer);
        //print out json
        return new ResponseEntity<>(sampleJson, HttpStatus.OK);
    }

    @GetMapping(value = "/getanddisplay")
    public ResponseEntity<String> getanddisplay() throws Exception {
        JSONObject sample = new JSONObject();
        sample.put("id", 1);
        return new ResponseEntity<>(sample.toString(), HttpStatus.OK);
    }
}