package com.example.recyclerview.adapter;

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
import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RecyclerHorizontalBinding;
import com.example.recyclerview.model.MyListItem;

import java.util.ArrayList;

public class CustomRecyclerAdatper extends RecyclerView.Adapter<CustomRecyclerAdatper.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<MyListItem> myListItem;

    public CustomRecyclerAdatper(Context context, Activity activity, ArrayList<MyListItem> myListItem) {
        this.context = context;
        this.activity = activity;
        this.myListItem = myListItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                + myListItem.get(position).getList().get(0).getUri());
        Glide.with(context).load(imagePath).into(holder.binding.ivImage);
        holder.binding.tvTitle.setText(myListItem.get(position).getList().get(0).getTitle());
        holder.binding.tvDescribe.setText(myListItem.get(position).getList().get(0).getDescribe());
    }

    @Override
    public int getItemCount() {
        return myListItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerHorizontalBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            initBinding();
        }

        private void initBinding(){
            binding = RecyclerHorizontalBinding.bind(itemView);
        }
    }
}
