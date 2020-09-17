package com.example.training.service;

import com.example.training.entity.Bus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusService {

    @GET("getAllBus-angular")
    Call<ArrayList<Bus>> getAllBus(@Query("id") String agencyId);
}
