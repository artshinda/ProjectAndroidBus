package com.example.training.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training.R;
import com.example.training.ViewHolder.BusViewHolder;
import com.example.training.entity.Bus;
import com.example.training.ui.DetailProfile.DetailActivity;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusViewHolder> {
    private List<Bus> busList;
    private Context mContext;

    public BusAdapter(List<Bus> busList, Context ctx){
        this.busList=busList;
        this.mContext= ctx;
    }


    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_bus,parent,false);
        return new BusViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        holder.code.setText(busList.get(position).getCode());
        holder.make.setText(busList.get(position).getMake());
        holder.capacity.setText(String.valueOf(busList.get(position).getCapacity()));
        Log.d("position", String.valueOf(busList.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailUser = new Intent(mContext, DetailActivity.class);
//                String s = ;
                detailUser.putExtra("busDetail",busList.get(position));
                mContext.startActivity(detailUser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

//@Override
//    public void onBindViewHolder(@NonNull UserViewHolder holder,final int position ){
//        holder.password.setText(userList.g);
//}
}
