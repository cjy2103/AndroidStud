package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.RecyclerGridBinding;

import java.util.ArrayList;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<String> imageList;



    public AlbumListAdapter(Context context, Activity activity, ArrayList<String> imageList) {
        this.context = context;
        this.activity = activity;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                + imageList.get(position));
        Glide.with(context).load(imagePath).into(holder.binding.imageView);
//        holder.binding.imageView.setImageURI(imagePath);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerGridBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();
        }

        private void viewBinding(){
            binding = RecyclerGridBinding.bind(itemView);
        }
    }
}
