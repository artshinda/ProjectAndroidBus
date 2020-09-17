package com.example.training.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    Profile mApiService;
    static Context mContext;
    SessionManager session;
//    private ProfileViewModel profileViewModel;
EditText editFirstName;
    EditText editLastName;
    EditText editMobileNumber;
    EditText editEmail;
    Button logOut;
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
        logOut          = root.findViewById(R.id.btnLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.deleteAllSharedPrefs();

                Intent agency=new Intent(ProfileFragment.mContext, LoginActivity.class);
                startActivity(agency);


            }
        });
        getUser();

        return root;
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