package com.example.training.service;

import com.example.training.entity.Register;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("createNewAccount")
    Call<ResponseBody> postRegister(@Body Register register);

}
