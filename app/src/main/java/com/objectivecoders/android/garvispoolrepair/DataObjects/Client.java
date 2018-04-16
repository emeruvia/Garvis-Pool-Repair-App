package com.objectivecoders.android.garvispoolrepair.DataObjects;


import java.util.Map;

/**
 * Created by emeruvia on 3/4/2018.
 */

public class Client {

    String id;
    String firstName;
    String lastName;
    String address;
    String email;
    Map<String,WorkOrder> workOrders;

    public Client() {

    }

    public Client(String id, String firstName, String lastName, String address, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

    public Client(String id, String firstName, String lastName, String address, String email,Map<String,WorkOrder> workOrders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.workOrders = workOrders;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(Map<String, WorkOrder> workOrderMap) {
        this.workOrders = workOrderMap;
    }
}

