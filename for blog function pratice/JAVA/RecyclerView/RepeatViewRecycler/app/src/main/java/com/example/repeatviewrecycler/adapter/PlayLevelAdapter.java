package com.example.repeatviewrecycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repeatviewrecycler.R;
import com.example.repeatviewrecycler.databinding.RecyclerPlayLevelBinding;
import com.example.repeatviewrecycler.util.LogUtil;

import java.util.ArrayList;

public class PlayLevelAdapter extends RecyclerView.Adapter<PlayLevelAdapter.ViewHolder>{

    private Context context;
    private ArrayList<String> playLevel;

    private onItemClickListener mListener = null;

    public void setOnItemClickListener(onItemClickListener listener){
        this.mListener = listener;
    }

    public PlayLevelAdapter(Context context, ArrayList<String> playLevel) {
        this.context = context;
        this.playLevel = playLevel;
        LogUtil.log("크기"+playLevel.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_play_level, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvPlayLevel.setText(playLevel.get(position));

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

            itemClick(itemView);
        }

        private void viewBinding(){
            binding = RecyclerPlayLevelBinding.bind(itemView);
        }

        private void itemClick(View itemView){
            itemView.setOnClickListener(v->{
                int pos = getAbsoluteAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    mListener.onItemClick(v, pos);
                }
            });
        }
    }
}
