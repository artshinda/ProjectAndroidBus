package com.example.training.ui.profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.training.HomeActivity;
import com.example.training.LoginActivity;
import com.example.training.R;
import com.example.training.entity.User;
import com.example.training.service.Profile;
import com.example.training.service.UtilsApi;
import com.example.training.util.SessionManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    Profile mApiService;
    static Context mContext;
    SessionManager session;
//    private ProfileViewModel profileViewModel;
    EditText editFirstName,editLastName, editMobileNumber, editEmail;
    TextView labelJoin,labelUsername;
    Button logOut;
    ProgressDialog loading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mContext=container.getContext();
        session=new SessionManager(mContext);
//        mContext = this;
        mApiService = UtilsApi.getProfileService();
//        profileViewModel = ViewModelProviders.of(this).get(pro);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        View contact = inflater.inflate(R.layout.fragment_profile, container,false);

        editFirstName      = root.findViewById(R.id.editFirstName);
        editLastName       = root.findViewById(R.id.editLastName);
        editMobileNumber   = root.findViewById(R.id.editMobileNumber);
        editEmail          = root.findViewById(R.id.editEmail);
        labelJoin          = root.findViewById(R.id.labelJoin);
        labelUsername      = root.findViewById(R.id.labelUsername);
        logOut          = root.findViewById(R.id.btnLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logout();
                    }
                });
            }
        });
        getUser();

        return root;
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setIcon(R.mipmap.ic_report); // Icon for your alertbox
        builder.setTitle("Permission");
        builder.setMessage("You sure, that you want to logout?");

        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        loading = ProgressDialog.show(mContext,
                                null, "Harap Menunggu",
                                true, false);
                        session.deleteAllSharedPrefs();

                        Intent i = new Intent(ProfileFragment.mContext,
                                LoginActivity.class);
                        Toast.makeText(mContext,"Anda Berhasil LogOut", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder.create();
        alert11.show();

//                session.deleteAllSharedPrefs();
//
//                loading.dismiss();
//                Intent agency=new Intent(ProfileFragment.mContext, LoginActivity.class);
//                Toast.makeText(mContext,"Anda Berhasil LogOut", Toast.LENGTH_SHORT).show();
//                startActivity(agency);
    }


    private void getUser(){
//harusnya yg ini
        String userId=session.getUserId();
//        String userId="824537d7-a1dd-4a4d-9d99-a3ed0a9103dd";

//        editFirstName.setText("lololo");
        mApiService.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> agCall, Response<User> agResponse) {
                if (agResponse.isSuccessful()){
                    Log.d("firstName",agResponse.body().getFirstName());
                    editFirstName.setText(agResponse.body().getFirstName());
                    editLastName.setText(agResponse.body().getLastName());
                    editMobileNumber.setText(agResponse.body().getMobileNumber());
                    editEmail.setText(agResponse.body().getEmail());
//                    String labelJoin = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));


                    Timestamp ts=new Timestamp(agResponse.body().getCreated_date());
                    Date date=new Date(ts.getTime());

                    String pattern = "dd MMMM yyyy";
                    SimpleDateFormat simpleDateFormat =
                            new SimpleDateFormat(pattern);

                    labelJoin.setText(simpleDateFormat.format(date));
                    labelUsername.setText(agResponse.body().getFirstName()+" "+agResponse.body().getLastName());
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