package com.example.repeatviewrecycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.repeatviewrecycler.R;
import com.example.repeatviewrecycler.databinding.RecyclerPlayLevelBinding;
import com.example.repeatviewrecycler.model.PlayLevel;
import com.example.repeatviewrecycler.util.LogUtil;

import java.util.ArrayList;

public class PlayLevelAdapter extends RecyclerView.Adapter<PlayLevelAdapter.ViewHolder>{

    private Context context;
    private ArrayList<PlayLevel> playLevel;

    private onItemClickListener mListener = null;

    public void setOnItemClickListener(onItemClickListener listener){
        this.mListener = listener;
    }

    public PlayLevelAdapter(Context context, ArrayList<PlayLevel> playLevel) {
        this.context = context;
        this.playLevel = playLevel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_play_level, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvPlayLevel.setText(playLevel.get(position).getList().get(0).getLevel());
        Glide.with(context).load(R.drawable.ic_play_level_normal).into(holder.binding.ivLevel);
        if(playLevel.get(position).getList().get(0).isChecked()){
            selectLevel(holder,position);
        }
    }

    private void selectLevel(ViewHolder holder, int position){
        switch (position) {
            case 0 :
                Glide.with(context).load(R.drawable.ic_play_level_beginner).into(holder.binding.ivLevel);
                break;
            case 1 :
                Glide.with(context).load(R.drawable.ic_play_level_intermediate).into(holder.binding.ivLevel);
                break;
            case 2 :
                Glide.with(context).load(R.drawable.ic_play_level_pro).into(holder.binding.ivLevel);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return playLevel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerPlayLevelBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();

            itemClick();
        }

        private void viewBinding(){
            binding = RecyclerPlayLevelBinding.bind(itemView);
        }

        private void itemClick(){
            binding.consPlayLevel.setOnClickListener(v->{
                int pos = getAbsoluteAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    mListener.onItemClick(v, pos);
                }
            });
        }
    }
}
