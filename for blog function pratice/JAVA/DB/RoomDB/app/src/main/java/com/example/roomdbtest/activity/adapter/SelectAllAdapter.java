package com.example.roomdbtest.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbtest.R;
import com.example.roomdbtest.activity.model.DataModel;
import com.example.roomdbtest.databinding.RecyclerSelectAllItemBinding;
import com.example.roomdbtest.util.LogUtils;

import java.util.ArrayList;

public class SelectAllAdapter extends RecyclerView.Adapter<SelectAllAdapter.ViewHolder>{

    private Context context;
    private ArrayList<DataModel> list;

    public SelectAllAdapter(Context context, ArrayList<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_select_all_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvTitle.setText(list.get(position).getTitle());
        holder.binding.tvMsg.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerSelectAllItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();
        }

        private void viewBinding(){
            binding = RecyclerSelectAllItemBinding.bind(itemView);
        }
    }
}
