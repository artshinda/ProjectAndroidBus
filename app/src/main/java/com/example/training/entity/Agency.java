package com.example.training.entity;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Agency {

    @SerializedName("id")
    private String id;
    private String code;

    private String details;
    private String name;
    private String owner;
    private String user_id;
    @SerializedName("createdDate")
    private Long created_date;
    private Long updated_date;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Long created_date) {
        this.created_date = created_date;
    }

    public Long getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Long updated_date) {
        this.updated_date = updated_date;
    }
}
