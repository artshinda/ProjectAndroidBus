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

import com.example.training.entity.Agency;
import com.example.training.service.Profile;
import com.example.training.service.UtilsApi;
import com.example.training.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    Button agency;
    Button bus;
    Profile mApiService;
    Context mContext;
    //    List<Bus> semuadosenItemList = new ArrayList<>();
    //        DosenAdapter dosenAdapter;
    SessionManager session;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText mobileNumber;
//    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session=new SessionManager(this);
        mContext = this;
        mApiService = UtilsApi.getProfileService();
        firstName=findViewById(R.id.editText);
        lastName=findViewById(R.id.editText2);
        email=findViewById(R.id.editText3);
        mobileNumber=findViewById(R.id.editText4);
//        password=findViewById(R.id.editText5);
        bus=findViewById(R.id.bus);
        agency=findViewById(R.id.agency);
        agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent agency=new Intent(HomeActivity.this, AgencyDetail.class);
                startActivity(agency);

            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent agency=new Intent(HomeActivity.this, BusActivity.class);
                startActivity(agency);

            }
        });

    }
    @Override
    protected void onResume() {
        getUser();
        super.onResume();
    }

    private void getUser(){
        String userId=session.getUserId();

        mApiService.getUser(userId).enqueue(new Callback<com.example.training.entity.User>() {
            @Override
            public void onResponse(Call<com.example.training.entity.User> agCall, Response<com.example.training.entity.User> agResponse) {
                if (agResponse.isSuccessful()){
                    Log.d("firstName",agResponse.body().getFirstName());
                    firstName.setText(agResponse.body().getFirstName());
                    lastName.setText(agResponse.body().getLastName());
                    email.setText(agResponse.body().getEmail());
                    mobileNumber.setText(agResponse.body().getMobileNumber());
//                    password.setText(agResponse.body().getPassword());
//                    edText2.setText(agResponse.body().getName());
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data detail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.training.entity.User> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}