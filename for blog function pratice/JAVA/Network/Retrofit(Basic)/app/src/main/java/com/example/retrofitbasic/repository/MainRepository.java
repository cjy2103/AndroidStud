package com.example.retrofitbasic.repository;

import com.example.retrofitbasic.model.PostResult;
import com.example.retrofitbasic.network.RetrofitClient;
import com.example.retrofitbasic.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private ServiceApi service;

    public void init(RetrofitCallback callback){
        service = RetrofitClient.getClient().create(ServiceApi.class);

        service.getPosts("1").enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                PostResult result = response.body();
                if (result != null) {
                    callback.onSuccess(result.toString());
                } else {
                    callback.onFailed();
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
