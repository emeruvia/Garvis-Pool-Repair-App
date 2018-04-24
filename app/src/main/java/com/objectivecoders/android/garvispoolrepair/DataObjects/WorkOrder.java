package com.objectivecoders.android.garvispoolrepair.DataObjects;

import java.util.Date;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrder {

    private String orderId;
    private String date;
    private String description;
    private String jobType;
    private boolean completed;


    public WorkOrder(){

    }

    public WorkOrder(String orderId, String date, String description, String jobType, boolean completed){
        this.completed = completed;
        this.date = date;
        this.description = description;
        this.jobType = jobType;
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getOrderNumber() {
        return orderId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderId = orderNumber;
    }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object obj) {
        WorkOrder workOrder = (WorkOrder) obj;
        return this.orderId.equals(workOrder.orderId);
    }
}
