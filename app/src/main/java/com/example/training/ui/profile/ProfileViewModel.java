package com.example.training.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mEditFirstName;
    private MutableLiveData<String> mEditLastName;
    private MutableLiveData<String> mEditMobileNumber;
    private MutableLiveData<String> mEditEmail;


    public ProfileViewModel() {
        mEditFirstName = new MutableLiveData<>();
        mEditFirstName.setValue("Syirfan");

        mEditLastName = new MutableLiveData<>();
        mEditLastName.setValue("Ibrahim");

        mEditMobileNumber = new MutableLiveData<>();
        mEditMobileNumber.setValue("081386153674");

        mEditEmail = new MutableLiveData<>();
        mEditEmail.setValue("syirfanahmad01@gmail.com");
    }

    public LiveData<String> getUsername() {
        return mEditFirstName;
    }

    public LiveData<String> getContactNumber(){
        return mEditLastName;
    }

    public LiveData<String> getMobileNumber(){
        return mEditMobileNumber;
    }

    public LiveData<String> getEmail(){
        return mEditEmail;
    }

}