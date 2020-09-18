//package com.example.training;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.training.entity.Agency;
//import com.example.training.service.UtilsApi;
//import com.example.training.service.getAgency;
//import com.example.training.util.SessionManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class AgencyDetail extends AppCompatActivity {
//    getAgency mApiService;
//    Context mContext;
//    EditText edText;
//    EditText edText2;
//    SessionManager session;
//    Button profile;
//    Button bus;
//    com.example.training.entity.Agency agency=new com.example.training.entity.Agency();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_agency_detail);
//        session=new SessionManager(this);
//        mContext = this;
//        edText=findViewById(R.id.editText);
//        edText2=findViewById(R.id.editText2);
//        profile=findViewById(R.id.profile);
//        bus=findViewById(R.id.bus);
//        mApiService = UtilsApi.getAgencyService();
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent agency=new Intent(AgencyDetail.this, HomeActivity.class);
//                startActivity(agency);
//
//            }
//        });
//
//        bus=findViewById(R.id.bus);
//        bus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent bus=new Intent(AgencyDetail.this, BusActivity.class);
//                startActivity(bus);
//
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        getDetailAgency();
//        super.onResume();
//    }
//
//    private void getDetailAgency(){
//        String agencyId=session.getAgencyId();
//
//        mApiService.getAgency(agencyId).enqueue(new Callback<Agency>() {
//            @Override
//            public void onResponse(Call<Agency> agCall, Response<Agency> agResponse) {
//                if (agResponse.isSuccessful()){
//                    Log.d("agencyId",agResponse.body().getDetails());
//                    edText.setText(agResponse.body().getDetails());
//                    edText2.setText(agResponse.body().getName());
//                } else {
//                    Toast.makeText(mContext, "Gagal mengambil data detail", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Agency> call, Throwable t) {
//                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}