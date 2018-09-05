package com.example.sqltest.web.model;

import com.example.sqltest.repository.model.Customer;

public class CustomerDTO {
    private String customerId;
    private String accountNum;
    private String status;
    private String createdBy;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String accountNum, String status, String createdBy) {
        this.customerId = customerId;
        this.accountNum = accountNum;
        this.status = status;
        this.createdBy = createdBy;
    }

    public static CustomerDTO map(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getAccountNum(), customer.getStatus(), customer.getCreatedBy());
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
