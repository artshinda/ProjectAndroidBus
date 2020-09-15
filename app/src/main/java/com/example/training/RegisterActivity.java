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
import android.widget.Toast;

import com.example.training.entity.Register;
import com.example.training.service.RegisterService;
import com.example.training.service.UtilsApi;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView shapeWhite, textLoginLink, textLogin;
    EditText editFirstName, editLastName, editEmail, editPassword,
             editRepeatPassword, editAgencyName, editAgencyDetail, editContactNumber;
    Button btnRegister;
    RegisterService mRegisterAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_registrasi);

        textLoginLink       = findViewById(R.id.textLoginLink);
        editFirstName       = findViewById(R.id.editFirstName);
        editLastName        = findViewById(R.id.editLastName);
        editEmail           = findViewById(R.id.editEmail);
        editPassword        = findViewById(R.id.editPassword);
        editRepeatPassword  = findViewById(R.id.editAgencyName);
        editAgencyName      = findViewById(R.id.editAgencyName);
        editAgencyDetail    = findViewById(R.id.editAgencyDetail);
        editContactNumber   = findViewById(R.id.editContactNumber);
        btnRegister         = findViewById(R.id.btnRegister);
        textLogin           = findViewById(R.id.textLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
                UIUtil.hideKeyboard(RegisterActivity.this);
            }
        });

        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = RegisterActivity.this.getCurrentFocus();
                if (focusedView != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                }
                return true;
            }
        });

        textLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent halLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(halLogin);
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent halLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(halLogin);
            }
        });
    }

    public void doRegister(){
        mRegisterAPIService = UtilsApi.getRegisterService();

        Register register = new Register();
        register.setFirstName(editFirstName.getText().toString());
        register.setLastName(editLastName.getText().toString());
        register.setEmail(editEmail.getText().toString());
        register.setPassword(editPassword.getText().toString());
        register.setAgencyName(editAgencyName.getText().toString());
        register.setAgencyDetail(editAgencyDetail.getText().toString());
        register.setContactNumber(editContactNumber.getText().toString());

        Call<ResponseBody> response = mRegisterAPIService.postRegister(register);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Intent goLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goLogin);
                    Toast.makeText(RegisterActivity.this, "Berhasil Terdaftar",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}