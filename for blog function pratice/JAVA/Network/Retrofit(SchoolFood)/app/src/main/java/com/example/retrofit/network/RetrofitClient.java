package com.example.retrofit.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static String   baseUri  = "https://open.neis.go.kr/hub/mealServiceDietInfo";

    public static void create(){
        if(retrofit == null){

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUri)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }
}
