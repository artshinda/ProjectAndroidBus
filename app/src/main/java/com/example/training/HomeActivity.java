package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.training.entity.User;
import com.example.training.service.AuthService;
import com.example.training.service.Profile;
import com.example.training.service.UtilsApi;
import com.example.training.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText mobileNumber;
    EditText password;
    Profile mApiService;
    Context mContext;
//    EditText edText;
    SessionManager session;
    Button agency;
    Button bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firstName   = findViewById(R.id.firstName);
        lastName   = findViewById(R.id.lastName);
        password   = findViewById(R.id.password);
        email   = findViewById(R.id.email);
        mobileNumber   = findViewById(R.id.mobileNumber);
        session=new SessionManager(this);
        mContext = this;
        mApiService = UtilsApi.getProfileService();
        agency=findViewById(R.id.agency);
        agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent agency=new Intent(HomeActivity.this, AgencyDetail.class);
                startActivity(agency);

            }
        });

        bus=findViewById(R.id.bus);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bus=new Intent(HomeActivity.this, BusActivity.class);
                startActivity(bus);

            }
        });
    }


    @Override
    protected void onResume() {
        getProfile();
        super.onResume();
    }

    private void getProfile() {
        String userId = session.getByKey("userId");

        mApiService.getProfile(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> userCall, Response<User> userResponse) {
                if (userResponse.isSuccessful()){
//                    Log.d("agencyId",userResponse.body().getDetails());
                    firstName.setText(userResponse.body().getFirstName());
                    lastName.setText(userResponse.body().getLastName());
                    email.setText(userResponse.body().getEmail());
                    mobileNumber.setText(userResponse.body().getMobileNumber());
                    password.setText(userResponse.body().getPassword());
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data detail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}