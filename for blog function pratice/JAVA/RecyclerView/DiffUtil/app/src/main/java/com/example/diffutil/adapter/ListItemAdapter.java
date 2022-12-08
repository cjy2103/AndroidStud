package com.example.diffutil.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diffutil.R;
import com.example.diffutil.databinding.RecyclerViewListBinding;
import com.example.diffutil.dto.ListItem;
import com.example.diffutil.util.DiffUtilCallback;
import com.example.diffutil.util.LogUtil;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ListItem> list;

    public ListItemAdapter(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list    = list;

    }

    public void updateListItem(ArrayList<ListItem> newList){
        DiffUtilCallback diffUtilCallback = new DiffUtilCallback(this.list, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        this.list.clear();
        this.list.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
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
                list.get(position).getUrl());
        Glide.with(context).load(uri).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(list.get(position).getTitle());
        holder.binding.tvDescribe.setText(list.get(position).getDescribe());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
