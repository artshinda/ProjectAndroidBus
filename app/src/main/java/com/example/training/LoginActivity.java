package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;

import com.example.training.service.AuthService;
import com.example.training.service.UtilsApi;
import com.example.training.util.SessionManager;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    Button btnLogin, btnSignUp;
    EditText editName, editEmail, editPassword;
    TextView textRegisLink, textRegis;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AuthService mAuthAPIService;
    SessionManager sessionManager;
    Context mContext;
    ProgressDialog loading;
    CheckBox checkShowPassword;

    public static String email;
    public static String password;
    public static String emailShared;
    public static String passwordShared;
    public static String value;
    public static final String resultText = "resultText";
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin          = findViewById(R.id.btnRegister);
        editEmail         = findViewById(R.id.editEmail);
        editPassword      = findViewById(R.id.editPassword);
        textRegisLink     = findViewById(R.id.textRegisLink);
        textRegis         = findViewById(R.id.textRegis);
        checkShowPassword = findViewById(R.id.checkShowPassword);
        sessionManager    = new SessionManager(this);
        mContext          = this;

        //Menyimpan Value editEmail
        sharedPreferences = getSharedPreferences(emailShared,Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(passwordShared,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        checkShowPassword.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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

        //Button click yang mengarah ke halaman registrasi
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailVal=editEmail.getText().toString();
                final String passwordVal=editPassword.getText().toString();

                if(editEmail.length()==0){
                    editEmail.requestFocus();
                    editEmail.setError("Email Cannot Be Empty");
                } else if(!emailVal.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")){
                    editEmail.requestFocus();
                    editEmail.setError("Enter The Right Email Address");
                } else if(passwordVal.length()==0) {
                    editPassword.requestFocus();
                    editPassword.setError("Password Cannot Be Empty");
                } else {
                    loading = ProgressDialog.show(mContext, null, "Harap Menunggu",
                            true, false);
                    doLogin();
                }
                UIUtil.hideKeyboard(LoginActivity.this);
            }
        });

        // Untuk unfocus keyboard ketika touch ke hal lain
        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = LoginActivity.this.getCurrentFocus();

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
                Intent halRegis = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(halRegis);
            }
        });

        // Untuk pindah halaman ke regis
        textRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent halRegis = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(halRegis);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
            return;
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

    public void doLogin(){
        mAuthAPIService = UtilsApi.getAuthService();
        mAuthAPIService.postLogin(editEmail.getText().toString(), editPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                sessionManager.setSession(jsonObject.getString("data"));
                                // Jika login berhasil maka data nama yang ada di response API
                                // akan diparsing ke activity selanjutnya.
                                Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_LONG).show();
                                Intent Home=new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(Home);
                            } catch (Exception e) {
                                Toast.makeText(mContext,"invalid email or password", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Toast.makeText(mContext,t.getMessage(), Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }
}