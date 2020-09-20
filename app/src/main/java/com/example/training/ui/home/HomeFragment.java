package com.example.training.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

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

    private int[] mImages = new int[]{
      R.drawable.bus_caraousel, R.drawable.bus_harapanjaya, R.drawable.bus_kramatjati
    };

    private String[] mImagesTitle = new String[]{
        "Bus Sinar Jaya", "Bus Harapan Jaya", "Bus Kramat Jati"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.activity_bus, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        CarouselView carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(mContext, mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView=root.findViewById(R.id.busRecycleView);
        mContext = container.getContext();
        session=new SessionManager(mContext);

        mApiService = UtilsApi.getBusService();
        getAllBus();
        return root;

//        dbAdapter=new DBAdapter(this);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
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