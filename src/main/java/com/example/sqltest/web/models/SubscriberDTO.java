package com.example.sqltest.web.models;

import javax.validation.constraints.NotNull;

public class SubscriberDTO {

    @NotNull
    private String customerId;

    private String accountNum;

    @NotNull
    private String status;

    private String createdBy;

    @NotNull
    private String serviceNum;

    public SubscriberDTO(@NotNull String customerId, String accountNum, @NotNull String status, String createdBy, @NotNull String serviceNum) {
        this.customerId = customerId;
        this.accountNum = accountNum;
        this.status = status;
        this.createdBy = createdBy;
        this.serviceNum = serviceNum;
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

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }
}
