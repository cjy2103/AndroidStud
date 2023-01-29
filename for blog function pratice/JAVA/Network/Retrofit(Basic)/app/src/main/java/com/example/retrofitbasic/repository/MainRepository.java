package com.example.retrofitbasic.repository;

import com.example.retrofitbasic.model.PostResult;
import com.example.retrofitbasic.network.RetrofitClient;
import com.example.retrofitbasic.network.ServiceApi;
import com.example.retrofitbasic.util.LogUtil;
import com.example.retrofitbasic.vm.MainViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private ServiceApi service;

    public void init(MainViewModel mainViewModel){
        service = RetrofitClient.getClient().create(ServiceApi.class);

        service.getPosts("1").enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                PostResult result = response.body();
                if(result!=null) {
                    mainViewModel.dataResponse(result.toString());
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                LogUtil.log("통신실패");
            }
        });
    }
}
