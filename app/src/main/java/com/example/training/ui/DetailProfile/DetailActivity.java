package com.example.training.ui.DetailProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.training.HomeActivity;
import com.example.training.R;
import com.example.training.entity.Bus;
import com.example.training.ui.home.HomeFragment;
import com.example.training.util.SessionManager;

public class DetailActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    Button btnKembali;
//    static Context mContext;
//    SessionManager session;
//    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bus);

//        mContext=container.getContext();
//        session=new SessionManager(mContext);

        textView1  = findViewById(R.id.code);
        textView2  = findViewById(R.id.make);
        textView3  = findViewById(R.id.capacity);
        btnKembali = findViewById(R.id.btnKembali);

        Bus user = (Bus) getIntent().getSerializableExtra("busDetail");
        textView1.setText(user.getCode());
        textView2.setText(user.getMake());
        textView3.setText(String.valueOf(user.getCapacity()));

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frame,new Fragment91()).addToBackStack(null).commit();
//                getActionBar().setHomeButtonEnabled(true);
//                getActionBar().setDisplayHomeAsUpEnabled(true);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                getSupportActionBar().setTitle("Dway Bus");
//                getSupportActionBar().setSubtitle("Detail Bus");

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                Intent kembali = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(kembali);
                Log.d("ssdsd", "dssd");
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // app icon in action bar clicked; goto parent activity.
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}