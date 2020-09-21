package com.example.training.ui.DetailProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
    String titleAction;
//    static Context mContext;
//    SessionManager session;
//    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bus);

//        mContext=container.getContext();
//        session=new SessionManager(mContext);

        TextView textView = (TextView) findViewById(R.id.editTextTextMultiLine);

        textView.setText("What is Lorem Ipsum? \\nLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "                \\nWhere does it come from? \\nContrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33. This book is a treatise on the theory of ethics, very popular during the Renaissance.");

        TextJustification.justify(textView);
        textView1  = findViewById(R.id.code);
        textView2  = findViewById(R.id.make);
        textView3  = findViewById(R.id.capacity);
//        btnKembali = findViewById(R.id.btnKembali);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dway Bus");
        getSupportActionBar().setSubtitle("Detail Bus");


        Bus user = (Bus) getIntent().getSerializableExtra("busDetail");
        textView1.setText(user.getCode());
        textView2.setText(user.getMake());
        textView3.setText(String.valueOf(user.getCapacity()));

//        btnKembali.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent kembali = new Intent(DetailActivity.this, HomeActivity.class);
//                startActivity(kembali);
//                Log.d("ssdsd", "dssd");
//            }
//        });
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