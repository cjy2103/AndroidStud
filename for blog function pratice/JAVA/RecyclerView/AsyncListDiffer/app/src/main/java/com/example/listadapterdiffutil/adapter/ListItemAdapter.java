package com.example.listadapterdiffutil.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.listadapterdiffutil.R;

import com.example.listadapterdiffutil.databinding.RecyclerViewListBinding;
import com.example.listadapterdiffutil.dto.ListItem;
import com.example.listadapterdiffutil.util.DiffItemCallback;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{

    private Context context;

    private AsyncListDiffer<ListItem> asyncListDiffer = new AsyncListDiffer<>(this, new DiffItemCallback());

    public ListItemAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        asyncListDiffer.submitList(list);
    }

    public void updateItem(ArrayList<ListItem> newItem){
        asyncListDiffer.submitList(newItem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() +
                asyncListDiffer.getCurrentList().get(position).getUrl());
        Glide.with(context).load(uri).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(asyncListDiffer.getCurrentList().get(position).getTitle());
        holder.binding.tvDescribe.setText(asyncListDiffer.getCurrentList().get(position).getDescribe());
    }

    @Override
    public int getItemCount() {
        return asyncListDiffer.getCurrentList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerViewListBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();
        }

        private void viewBinding(){
            binding = RecyclerViewListBinding.bind(itemView);
        }
    }
}
