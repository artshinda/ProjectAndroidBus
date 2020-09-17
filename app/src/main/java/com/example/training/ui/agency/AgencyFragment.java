package com.example.training.ui.agency;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.training.R;
import com.example.training.entity.Agency;
import com.example.training.entity.User;
import com.example.training.service.Profile;
import com.example.training.service.UtilsApi;
import com.example.training.service.getAgency;
import com.example.training.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyFragment extends Fragment {

    getAgency mApiService;
    static Context mContext;
    SessionManager session;
    //    private ProfileViewModel profileViewModel;
    EditText editAgencyName;
    EditText editAgencyDetail;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mContext=container.getContext();
        session=new SessionManager(mContext);
//        mContext = this;
        mApiService = UtilsApi.getAgencyService();
//        profileViewModel = ViewModelProviders.of(this).get(pro);
        View root = inflater.inflate(R.layout.fragment_agency, container, false);
        View contact = inflater.inflate(R.layout.fragment_agency, container,false);

        editAgencyName      = root.findViewById(R.id.editAgencyName);
        editAgencyDetail       = root.findViewById(R.id.editAgencyDetail);
        getAgency();

        return root;
    }

    private void getAgency(){
//harusnya yg ini
        String agencyId=session.getAgencyId();
//        String userId="824537d7-a1dd-4a4d-9d99-a3ed0a9103dd";

//        editFirstName.setText("lololo");
        mApiService.getAgency(agencyId).enqueue(new Callback<Agency>() {
            @Override
            public void onResponse(Call<Agency> agCall, Response<Agency> agResponse) {
                if (agResponse.isSuccessful()){
                    Log.d("firstName",agResponse.body().getName());
                    editAgencyName.setText(agResponse.body().getName());
                    editAgencyDetail.setText(agResponse.body().getDetails());
//                    editMobileNumber.setText(agResponse.body().getMobileNumber());
//                    editEmail.setText(agResponse.body().getEmail());
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data detail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Agency> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}