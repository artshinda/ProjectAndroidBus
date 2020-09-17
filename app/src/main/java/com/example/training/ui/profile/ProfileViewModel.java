//package com.example.training.ui.profile;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.training.entity.User;
//import com.example.training.service.Profile;
//import com.example.training.service.getAgency;
//import com.example.training.util.SessionManager;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ProfileViewModel extends ViewModel {
////    Profile mApiService;
////    Context mContext;
//    SessionManager session;
//    private MutableLiveData<String> mEditFirstName;
//    private MutableLiveData<String> mEditLastName;
//    private MutableLiveData<String> mEditMobileNumber;
//    private MutableLiveData<String> mEditEmail;
//
//    public ProfileViewModel() {
//        session=new SessionManager(this);
//        mEditFirstName = new MutableLiveData<>();
//        mEditFirstName.setValue("Syirfan");
//
//        mEditLastName = new MutableLiveData<>();
//        mEditLastName.setValue("Ibrahim");
//
//        mEditMobileNumber = new MutableLiveData<>();
//        mEditMobileNumber.setValue("081386153674");
//
//        mEditEmail = new MutableLiveData<>();
//        mEditEmail.setValue("syirfanahmad01@gmail.com");
//    }
//
//    private void getUser(){
//        String userId=session.getUserId();
//
//        mApiService.getUser(userId).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> agCall, Response<User> agResponse) {
//                    Log.d("firstName",agResponse.body().getFirstName());
//                    firstName.setText(agResponse.body().getFirstName());
//                    lastName.setText(agResponse.body().getLastName());
//                    email.setText(agResponse.body().getEmail());
//                    mobileNumber.setText(agResponse.body().getMobileNumber());
////                    password.setText(agResponse.body().getPassword());
////                    edText2.setText(agResponse.body().getName());
//
//            }
//
//        });
//    }
//    public LiveData<String> getUsername() {
//        return mEditFirstName;
//    }
//
//    public LiveData<String> getContactNumber(){
//        return mEditLastName;
//    }
//
//    public LiveData<String> getMobileNumber(){
//        return mEditMobileNumber;
//    }
//
//    public LiveData<String> getEmail(){
//        return mEditEmail;
//    }
//
//}