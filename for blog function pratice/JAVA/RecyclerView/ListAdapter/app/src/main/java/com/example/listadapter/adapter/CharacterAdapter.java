package com.example.listadapter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadapter.R;
import com.example.listadapter.databinding.RecyclerViewListBinding;
import com.example.listadapter.model.Character;
import com.example.listadapter.util.CharacterDiffUtil;

import java.util.ArrayList;


public class CharacterAdapter extends ListAdapter<Character, CharacterAdapter.ViewHolder> {

    private ArrayList<Character> list;

    public CharacterAdapter(){
        super(new CharacterDiffUtil());
        this.list = new ArrayList<>();
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
        holder.binding.setPosition(position);
        holder.binding.executePendingBindings();
    }

    public void setList(ArrayList<Character> list) {
        this.list = list;
        submitList(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerViewListBinding binding;

        public ViewHolder(RecyclerViewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setDataList(list);
        }

        @BindingAdapter("app:srcCompat")
        public static void setImage(ImageView imageView, Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }
    }
}
