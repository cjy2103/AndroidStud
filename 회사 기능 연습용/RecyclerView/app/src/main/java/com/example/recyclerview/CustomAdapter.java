package com.example.recyclerview;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private ArrayList<Dictionary> mList;



    public class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView id;
        protected TextView english;
        protected TextView korean;

        public CustomViewHolder(View view){
            super(view);
            this.id = (TextView) view.findViewById(R.id.id_listitem);
            this.english = (TextView) view.findViewById(R.id.english_listitem);
            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
        }
    }

    public CustomAdapter(ArrayList<Dictionary> list){
        this.mList = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position) {
        viewHolder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewHolder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewHolder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewHolder.id.setGravity(Gravity.CENTER);
        viewHolder.english.setGravity(Gravity.CENTER);
        viewHolder.korean.setGravity(Gravity.CENTER);

        viewHolder.id.setText(mList.get(position).getId());
        viewHolder.english.setText(mList.get(position).getEnglish());
        viewHolder.korean.setText(mList.get(position).getKorean());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
