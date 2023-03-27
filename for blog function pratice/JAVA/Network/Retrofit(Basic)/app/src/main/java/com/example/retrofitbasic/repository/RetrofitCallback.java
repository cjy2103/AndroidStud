package com.example.retrofitbasic.repository;

public interface RetrofitCallback {
    default void onSuccess(String result){}
    default void onFailed(){}
    default void onError(Throwable throwable){}
}
