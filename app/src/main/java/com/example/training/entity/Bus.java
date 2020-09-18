package com.example.training.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Bus implements Serializable {

    @SerializedName("id")
    private String id;
    private String code;

    private String agency_id;
    private Integer capacity;

    private String make;
    private Date created_date;
    private Date updated_date;
//    private List<Bus> listBus;

    //    public void setAllBus(List<Bus> listBus){
//        this.listBus = listBus;
//    }
//
//    public List<Bus> getAllBus(){
//        return listBus;
//    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAgencyId() {
        return agency_id;
    }

    public void setAgencyId(String agencyId) {
        this.agency_id = agency_id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Date getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Date createdDate) {
        this.created_date = createdDate;
    }

    public Date getUpdatedDate() {
        return updated_date;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updated_date = updatedDate;
    }
}
