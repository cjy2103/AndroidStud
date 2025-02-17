package com.example.roomrecyclerview.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.RecyclerLocalAlbumBinding;

import java.util.ArrayList;

public class LocalAlbumListAdapter extends RecyclerView.Adapter<LocalAlbumListAdapter.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<String> imageList;

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private LocalAlbumListAdapter.OnItemClickListener listener = null;

    public void setOnItemClickListener(LocalAlbumListAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    public LocalAlbumListAdapter(Context context, Activity activity, ArrayList<String> imageList) {
        this.context = context;
        this.activity = activity;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_local_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                        + imageList.get(position));
        Glide.with(context).load(imagePath).into(holder.binding.ivImage);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerLocalAlbumBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();
            itemClick();
        }

        private void viewBinding(){
            binding = RecyclerLocalAlbumBinding.bind(itemView);
        }

        private void itemClick(){
            itemView.setOnClickListener(v->{
                int pos = getBindingAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    listener.onItemClick(v,pos);
                }
            });
        }
    }
}
