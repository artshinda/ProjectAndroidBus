package com.example.training.service;

import com.example.training.entity.Agency;
import com.example.training.util.ApiClient;

public class UtilsApi {
    public static AuthService getAuthService(){
        return ApiClient.getClient().create(AuthService.class);
    }
    public static Profile getProfileService(){
        return ApiClient.getClient().create(Profile.class);
    }
    public static getAgency getAgencyService(){
        return ApiClient.getClient().create(getAgency.class);
    }

    public static RegisterService getRegisterService(){
        return ApiClient.getClient().create(RegisterService.class);
    }

    public static BusService getBusService(){
        return ApiClient.getClient().create(BusService.class);
    }

}

