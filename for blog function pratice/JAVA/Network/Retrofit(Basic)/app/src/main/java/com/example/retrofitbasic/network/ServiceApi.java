package com.example.retrofitbasic.network;

import com.example.retrofitbasic.model.PostResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi {

    @GET("posts/{post}")
    Call<PostResult> getPosts(@Path("post")String post);
}
