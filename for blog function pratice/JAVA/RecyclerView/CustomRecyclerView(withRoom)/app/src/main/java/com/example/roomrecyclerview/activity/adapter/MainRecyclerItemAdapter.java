package com.example.roomrecyclerview.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomrecyclerview.databinding.RecyclerViewListBinding;

import java.util.ArrayList;

public class MainRecyclerItemAdapter extends RecyclerView.Adapter<MainRecyclerItemAdapter.ViewHolder>{

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerViewListBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            viewBinding();
        }

        private void viewBinding(){
            binding = RecyclerViewListBinding.bind(itemView);
        }
    }
}
