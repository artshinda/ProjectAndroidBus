package com.example.training.ui.DetailProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.training.R;
import com.example.training.entity.Bus;

public class DetailActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bus);

        textView1=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        Bus user = (Bus) getIntent().getSerializableExtra("busDetail");
        textView1.setText(user.getCode());
        textView2.setText(user.getMake());
        textView3.setText(String.valueOf(user.getCapacity()));
    }
}