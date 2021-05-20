package com.example.youtubetest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubetest.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private ArrayList<String> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public MyAdapter(ArrayList<String> itemList, Context context, View.OnClickListener onClickItem) {
        this.itemList = itemList;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //context 와 parent.getContext()는 같다
        View view = LayoutInflater.from(context).inflate(R.layout.time,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String item = itemList.get(position);

            holder.textView.setText(item);
            holder.textView.setTag(item);
            holder.textView.setOnClickListener(onClickItem);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.time);
        }
    }
}
