package com.example.sqltest.web.models;

import com.example.sqltest.repository.model.Subscriber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class CustomerDTO {

    @NotNull
    private int rid;

    @NotNull
    private String customerId;

    @NotNull
    private String accountNum;

    @NotNull
    private String status;

    @NotNull
    private String createdBy;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String accountNum, String status, String createdBy) {
        this.customerId = customerId;
        this.accountNum = accountNum;
        this.status = status;
        this.createdBy = createdBy;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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
