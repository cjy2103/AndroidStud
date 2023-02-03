package com.example.recyclerviewmvvm.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmvvm.R;
import com.example.recyclerviewmvvm.databinding.RecyclerViewListBinding;
import com.example.recyclerviewmvvm.model.Character;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder>{

    private ArrayList<Character> list;

    public CharacterAdapter(ArrayList<Character> list) {
        this.list    = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recycler_view_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setDataList(list);
        holder.binding.setPosition(position);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerViewListBinding binding;

        public ViewHolder(RecyclerViewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @BindingAdapter("app:srcCompat")
        public static void setImage(ImageView imageView, Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }
    }
}
