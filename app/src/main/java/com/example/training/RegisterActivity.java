package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    CheckBox checkShowPassword;
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
        checkShowPassword   = findViewById(R.id.checkShowPassword);
        editRepeatPassword  = findViewById(R.id.editAgencyName);
        editAgencyName      = findViewById(R.id.editAgencyName);
        editAgencyDetail    = findViewById(R.id.editAgencyDetail);
        editContactNumber   = findViewById(R.id.editContactNumber);
        btnRegister         = findViewById(R.id.btnRegister);
        textLogin           = findViewById(R.id.textLogin);

        checkShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // TODO Auto-generated method stub
                if (!isChecked) {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstNameVal=editFirstName.getText().toString();
                final String lastNameVal=editLastName.getText().toString();
                final String emailVal=editEmail.getText().toString();
                final String passwordVal=editPassword.getText().toString();
                final String agencyNameVal=editAgencyName.getText().toString();
                final String agencyDetailVal=editAgencyDetail.getText().toString();
                final String contactNumberVal=editContactNumber.getText().toString();

                if(firstNameVal.length()==0){
                    editFirstName.requestFocus();
                    editFirstName.setError("First Name Cannot Be Empty");
                } else if(!firstNameVal.matches("[a-zA-Z ]+")){
                    editFirstName.requestFocus();
                    editFirstName.setError("Enter Only Alphabetical Character");
                } else if(lastNameVal.length()==0) {
                    editLastName.requestFocus();
                    editLastName.setError("Last Name Cannot Be Empty");
                } else if(!lastNameVal.matches("[a-zA-Z ]+")){
                    editLastName.requestFocus();
                    editLastName.setError("Enter Only Alphabetical Character");
                } else if(emailVal.length()==0) {
                    editEmail.requestFocus();
                    editEmail.setError("Email Cannot Be Empty");
                } else if(!emailVal.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")){
                    editEmail.requestFocus();
                    editEmail.setError("Enter The Right Email Address");
                } else if(passwordVal.length()==0) {
                    editPassword.requestFocus();
                    editPassword.setError("Password Cannot Be Empty");
                } else if(agencyNameVal.length()==0) {
                    editAgencyName.requestFocus();
                    editAgencyName.setError("Agency Name Cannot Be Empty");
                } else if(!agencyNameVal.matches("[a-zA-Z ]+")){
                    editAgencyName.requestFocus();
                    editAgencyName.setError("Enter Only Alphabetical Character");
                } else if(agencyDetailVal.length()==0) {
                    editAgencyDetail.requestFocus();
                    editAgencyDetail.setError("Agency Detail Cannot Be Empty");
                } else {
                    doRegister();
                }
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