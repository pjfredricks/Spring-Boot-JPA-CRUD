package com.example.sqltest.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A class for defining the structure of our subscriber table
 * @author Jfredricks
 * @version 1.0
 */
@Table(name = "subscriber")
@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private int rid;

    @NotNull
    @Column(name = "customerId")
    private String customerId;

    @NotNull
    @Column(name = "accountNum")
    private String accountNum;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "createdBy")
    private String createdBy;

    @NotNull
    @Column(name = "serviceNum")
    private String serviceNum;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "customerId"))
    private Customer customer;

    /**
     * Getter method for customerId
     *
     * @return customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Setter method for customerId
     * @param customerId is set to passed value
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter method for accountNum
     * @return accountNum
     */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * Setter method for accountNum
     * @param accountNum is set to passed value
     */
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * Getter method for status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for status
     * @param status is set to passed value
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for createdBy
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter method for createdBy
     * @param createdBy is set to passed value
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter method for rid
     * @return rid
     */
    public int getRid() {
        return rid;
    }

    /**
     * Setter method for  rid
     * @param rid is set to passed value
     */
    public void setRid(int rid) {
        this.rid = rid;
    }

    /**
     * Getter method for serviceNum
     * @return serviceNum
     */
    public String getServiceNum() {
        return serviceNum;
    }

    /**
     * Setter method for  serviceNum
     * @param serviceNum is set to passed value
     */
    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

}
