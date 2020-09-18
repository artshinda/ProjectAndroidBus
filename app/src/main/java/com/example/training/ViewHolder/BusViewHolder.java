package com.example.training.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training.R;

public class BusViewHolder extends RecyclerView.ViewHolder {
    public TextView code, make,capacity;
    public BusViewHolder(@NonNull View itemView){
        super(itemView);
        code=(TextView) itemView.findViewById(R.id.code);
        make=(TextView) itemView.findViewById(R.id.make);
        capacity=(TextView) itemView.findViewById(R.id.capacity);
    }
}
