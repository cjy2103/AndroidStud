package com.example.roomrecyclerview.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.model.MyListItem;
import com.example.roomrecyclerview.databinding.RecyclerViewListBinding;

import java.util.ArrayList;

public class MainRecyclerItemAdapter extends RecyclerView.Adapter<MainRecyclerItemAdapter.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<MyListItem> listItems;

    public MainRecyclerItemAdapter(Context context, Activity activity, ArrayList<MyListItem> listItems) {
        this.context = context;
        this.activity = activity;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(listItems.get(position).getList().get(0).getImageCase().equals("Local")){
            caseLocal(holder, position);
        } else {
            caseGallery(holder, position);
        }

    }

    private void caseLocal(ViewHolder holder, int position){
        Uri imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                + listItems.get(position).getList().get(0).getUri());
        Glide.with(context).load(imagePath).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(listItems.get(position).getList().get(0).getTitle());
        holder.binding.tvDescribe.setText(listItems.get(position).getList().get(0).getDescribe());
    }

    private void caseGallery(ViewHolder holder, int position){
        Uri imagePath =  Uri.parse(listItems.get(position).getList().get(0).getUri());
        Glide.with(context).load(imagePath).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(listItems.get(position).getList().get(0).getTitle());
        holder.binding.tvDescribe.setText(listItems.get(position).getList().get(0).getDescribe());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
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
