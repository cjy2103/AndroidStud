package com.example.bottomnavigationtest.util;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationtest.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    private Context context;
    private ArrayList<MusicModel> musicModels;
    private byte[] bytes;

    public MusicAdapter(Context context, ArrayList<MusicModel> musicModels) {
        this.context = context;
        this.musicModels = musicModels;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MusicAdapter.ViewHolder holder, int position) {

        Uri uri = Uri.parse(musicModels.get(position).getUriString());
        String albumid = musicModels.get(position).getAlbumId();
        String imagePath = musicModels.get(position).getImagePath();
        Uri imagePathUri = Uri.parse(imagePath);

        Handler handler = new Handler();

        new Thread(()->{
            handler.post(()->{
               if(imagePathUri!=null){
                   Glide.with(context).load(imagePathUri).placeholder(R.drawable.img_chun).into(holder.imgAlbum);
               }
            });
        }).start();


        holder.tvTitle.setText(musicModels.get(position).getTitle());
        holder.tvArtist.setText(musicModels.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgAlbum;
        private TextView tvTitle;
        private TextView tvArtist;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgAlbum    = itemView.findViewById(R.id.img_album);
            tvTitle     = itemView.findViewById(R.id.tv_title);
            tvArtist    = itemView.findViewById(R.id.tv_artist);
        }
    }
}
