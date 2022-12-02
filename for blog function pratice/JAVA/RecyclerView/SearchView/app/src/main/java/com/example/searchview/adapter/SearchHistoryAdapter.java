package com.example.searchview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchview.R;
import com.example.searchview.databinding.RecyclerSearchHistoryBinding;

import java.util.LinkedList;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>{

    private Context context;
    private LinkedList<String> list;

    private OnItemClickListener itemClickListener = null;
    private OnItemClickListener deleteListener = null;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setDeleteListener(OnItemClickListener deleteListener){
        this.deleteListener = deleteListener;
    }

    public SearchHistoryAdapter(Context context, LinkedList<String> list) {
        this.context = context;
        this.list    = list;
    }

    public void setList(LinkedList<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_search_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvWord.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerSearchHistoryBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding();
            itemClick();
            deleteItem();
        }

        private void viewBinding(){
            binding = RecyclerSearchHistoryBinding.bind(itemView);
        }

        private void itemClick(){
            itemView.setOnClickListener(v->{
                int pos = getBindingAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    itemClickListener.onItemClick(v,pos);
                }
            });
        }

        private void deleteItem(){
            binding.consCancel.setOnClickListener(v->{
                int pos = getBindingAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    deleteListener.onItemClick(v, pos);
                }
            });
        }
    }
}
