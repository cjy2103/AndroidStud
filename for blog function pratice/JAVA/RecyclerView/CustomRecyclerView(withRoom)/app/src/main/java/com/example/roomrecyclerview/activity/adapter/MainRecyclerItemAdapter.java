package com.example.roomrecyclerview.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.model.MyListItem;
import com.example.roomrecyclerview.databinding.RecyclerViewListBinding;

import java.util.ArrayList;

public class MainRecyclerItemAdapter extends RecyclerView.Adapter<MainRecyclerItemAdapter.ViewHolder>{

    private Context context;
    private ArrayList<MyListItem> listItems;

    public MainRecyclerItemAdapter(Context context, ArrayList<MyListItem> listItems) {
        this.context = context;
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
