package com.mukeshkpdeveloper.mytask.UI.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.models.ErrorPojoClass;
import com.mukeshkpdeveloper.mytask.networking.RetrofitClient;
import com.mukeshkpdeveloper.mytask.utils.AppPreference;
import com.mukeshkpdeveloper.mytask.utils.Constant;
import com.mukeshkpdeveloper.mytask.utils.Util;
import com.mukeshkpdeveloper.mytask.utils.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Context context;
    TextInputEditText etUsername, etPassword;
    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    Button btnLogin;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login_);
        textInputLayoutUsername = findViewById(R.id.textInputLayout2);
        textInputLayoutPassword = findViewById(R.id.textInputLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              startActivity(new Intent(LoginActivity.this, MainActivity.class));

                if (validation()) {
                    loginApi();
                }

            }
        });

    }

    private boolean validation() {
        try {
            username = etUsername.getText().toString().trim();
            password = etPassword.getText().toString().trim();

            if (Utilities.isEmpty(username)) {
                textInputLayoutUsername.setError("Invalid Email address, ex: abc@example.com");
                etUsername.requestFocus();
                return false;

            } else if (Utilities.isEmpty(password)) {
                etPassword.requestFocus();
                textInputLayoutPassword.setError("Password is required");
                return false;

            }

        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }

        return true;
    }

    private void loginApi() {
        if (Util.isNetworkAvailable(context)) {
            Util.showProgressBar(context, true);
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("email", username);
            requestBody.put("password", password);

            Call<JsonObject> call = RetrofitClient.getInstance().getApiInterface().login(requestBody);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Util.showProgressBar(context, false);

                    if (response.code() == 200) {
                        String token = String.valueOf(response.body().get("token"));

//                                Util.showShortToast(context, "Working"+token);

                        AppPreference.setStringPreference(context, Constant.TOKEN, token);
                        Log.i("token-----------------", "" + token);
                        AppPreference.setBooleanPreference(context, Constant.IS_LOGIN, true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    } else if (response.code() == 500) {
                        Util.showShortToast(context, "Incorrect credentials!");

                    } else if (response.code() == 400) {
                        Gson gson = new GsonBuilder().create();
                        ErrorPojoClass mError = new ErrorPojoClass();
                        try {
                            mError = gson.fromJson(response.errorBody().string(), ErrorPojoClass.class);
                            Toast.makeText(context, mError.getError(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            // handle failure to read error
                        }
                    } else {
                        Util.showShortToast(context, "Internal Error...");
                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Util.showProgressBar(context, false);
                    Util.showShortToast(context, t.getMessage());
                    Util.log(t.getMessage());
                }
            });

        }
    }
}