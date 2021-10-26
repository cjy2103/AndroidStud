package com.example.viewmodeltest;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @org.jetbrains.annotations.NotNull Class<T> modelClass) {
        try {
            return modelClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("팩토리 런타임 에러");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("팩토리 런타임 에러2");
        }

    }
}
