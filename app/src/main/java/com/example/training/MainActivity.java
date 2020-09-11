package com.example.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;
    EditText editName, editEmail, editPassword;
    TextView textRegisLink;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static String email;
    public static String password;
    public static String emailShared;
    public static String passwordShared;
    public static String value;
    public static final String resultText = "resultText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin      = findViewById(R.id.btnLogin);
        editEmail     = findViewById(R.id.editEmail);
        editPassword  = findViewById(R.id.editPassword);
        textRegisLink = findViewById(R.id.textRegisLink);

        //Menyimpan Value editEmail
        sharedPreferences = getSharedPreferences(emailShared,Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(passwordShared,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Button click yang mengarah ke halaman registrasi
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, editEmail.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, editPassword.getText(), Toast.LENGTH_SHORT).show();

                UIUtil.hideKeyboard(MainActivity.this);
            }
        });

        // Untuk unfocus keyboard ketika touch ke hal lain
        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = MainActivity.this.getCurrentFocus();

                if (focusedView != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                }
                return true;
            }
        });

        // Untuk pindah halaman ke regis
        textRegisLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent halRegis = new Intent(MainActivity.this, HalamanRegistrasi.class);
                startActivity(halRegis);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart Invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        editEmail.setText(emailShared);
        editPassword.setText(passwordShared);
//        editEmail.setText(email);
//        editPassword.setText(password);
        Log.d("lifecycle", "onResume Invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
          emailShared = editEmail.getText().toString();
          editor      = sharedPreferences.edit();
          editor.putString(resultText, emailShared);
          editor.commit();
          value       = sharedPreferences.getString(resultText,"");

          passwordShared = editPassword.getText().toString();
          editor         = sharedPreferences.edit();
          editor.putString(resultText, passwordShared);
          editor.commit();
          value          = sharedPreferences.getString(resultText,"");

//        email = editEmail.getText().toString();
//        password = editPassword.getText().toString();
        Log.d("lifecycle", "onPause Invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop Invoked");
    }

//    private void closeKeyboard(){
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager close = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            close.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }

//    public static void hideSoftKeyboard(MainActivity activity) {
//        InputMethodManager inputMethodManager =
//                (InputMethodManager) activity.getSystemService(
//                        MainActivity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(
//                activity.getCurrentFocus().getWindowToken(), 0);
//    }
}