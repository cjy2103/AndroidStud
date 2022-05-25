package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dropbox.core.v2.files.Metadata;
import com.example.myapplication.R;
import com.example.myapplication.databinding.RecyclerDataRowBinding;

import java.util.List;

public class FileRecyclerAdapter extends RecyclerView.Adapter<FileRecyclerAdapter.ViewHolder>{

    private Context context;
    private List fileList;

    public FileRecyclerAdapter(Context context, List fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private FileRecyclerAdapter.OnItemClickListener listener = null;

    public void setOnItemClickListener(FileRecyclerAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_data_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Metadata item = (Metadata) fileList.get(position);
        String name = item.getName();
        holder.binding.tvMain.setText(name);
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerDataRowBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();
            itemClick();
        }

        private void viewBinding(){
            binding = RecyclerDataRowBinding.bind(itemView);
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
