package com.example.recyclerex2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerex2.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private ArrayList<String>mData = null;
    private static final String TAG = TestAdapter.class.getSimpleName();

    public TestAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

    }


    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.v(TAG,"onCreateViewHolder 탔음");

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view  = inflater.inflate(R.layout.recyclerview_item, parent, false);
        TestAdapter.ViewHolder vh = new TestAdapter.ViewHolder(view);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {

        Log.v(TAG,"onBindViewHolder 탔음");

        String text = mData.get(position);
        holder.textView.setText(text);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
