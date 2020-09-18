package com.example.training.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training.R;
import com.example.training.adapter.BusAdapter;
import com.example.training.entity.Bus;
import com.example.training.service.BusService;
import com.example.training.service.UtilsApi;
import com.example.training.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    BusAdapter bAdapter;
    SessionManager session;
    BusService mApiService;
    Context mContext;
    List<Bus> buses=new ArrayList<>();
//    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.activity_bus, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        recyclerView=root.findViewById(R.id.busRecycleView);
        mContext = container.getContext();
        session=new SessionManager(mContext);
        mApiService = UtilsApi.getBusService();
//        dbAdapter=new DBAdapter(this);
        getAllBus();
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void getAllBus(){
        String agencyId=session.getAgencyId();

        mApiService.getAllBus(agencyId).enqueue(new Callback<ArrayList<Bus>>() {
            @Override
            public void onResponse(Call<ArrayList<Bus>> agCall, Response<ArrayList<Bus>> agResponse) {
                if (agResponse.isSuccessful()){
                    buses = agResponse.body();
//                    System.out.println(buses);

                    bAdapter=new BusAdapter(buses,mContext);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(bAdapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data detail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Bus>> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}