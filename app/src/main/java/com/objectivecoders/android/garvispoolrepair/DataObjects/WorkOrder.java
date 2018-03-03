package com.objectivecoders.android.garvispoolrepair.DataObjects;

import java.util.Date;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrder {

    private int orderNumber;
    private Date date;
    private String address;
    private String description;
    private String jobType;

    public WorkOrder(int orderNumber,Date date, String address,String description, String jobType){
        this.address = address;
        this.date = date;
        this.description = description;
        this.jobType = jobType;
        this.orderNumber = orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
