package com.example.training.service;

import com.example.training.util.ApiClient;

public class UtilsApi {
    public static AuthService getAuthService(){
        return ApiClient.getClient().create(AuthService.class);
    }

    public static RegisterService getRegisterService(){
        return ApiClient.getClient().create(RegisterService.class);
    }
}
