package com.mukeshkpdeveloper.mytask.networking;

import com.mukeshkpdeveloper.mytask.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientTwo { private static Retrofit retrofit = null;

    private static RetrofitClientTwo instance = null;
    private ApiInterface apiInterface;

    private RetrofitClientTwo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static synchronized RetrofitClientTwo getInstance() {
        if (instance == null) {
            instance = new RetrofitClientTwo();
        }
        return instance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();
}