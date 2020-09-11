package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class HalamanRegistrasi extends AppCompatActivity {

    TextView shapeWhite, textLoginLink;
    EditText editFirstName, editLastName, editEmail, editPassword, editRepeatPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_registrasi);

        textLoginLink       = findViewById(R.id.textLoginLink);
        editFirstName       = findViewById(R.id.editEmail);
        editLastName        = findViewById(R.id.editLastName);
        editEmail           = findViewById(R.id.editEmail);
        editPassword        = findViewById(R.id.editPassword);
        editRepeatPassword  = findViewById(R.id.editRepeatPassword);
        btnRegister         = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtil.hideKeyboard(HalamanRegistrasi.this);
            }
        });

        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = HalamanRegistrasi.this.getCurrentFocus();
                if (focusedView != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                }
                return true;
            }
        });

        textLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent halLogin = new Intent(HalamanRegistrasi.this, MainActivity.class);
                startActivity(halLogin);
            }
        });
    }
}