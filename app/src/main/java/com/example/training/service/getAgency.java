package com.example.training.service;

import com.example.training.entity.Agency;
import com.example.training.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getAgency {
    @GET("getAgencyApi")
    Call<Agency> getAgency(@Query("id") String agencyId);

    @GET("getUserId")
    Call<User> getUser(@Query("id") String userId);
}
