package com.example.customrecyclerviewdetail.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.customrecyclerviewdetail.R;
import com.example.customrecyclerviewdetail.activity.RecyclerItemDetailActivity;
import com.example.customrecyclerviewdetail.databinding.RecyclerViewListBinding;
import com.example.customrecyclerviewdetail.model.ListItemModel;
import com.example.customrecyclerviewdetail.model.MyListItem;

import java.util.ArrayList;

public class CustomRecyclerAdatper extends RecyclerView.Adapter<CustomRecyclerAdatper.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<MyListItem> myListItem;
    private ArrayList<Integer> searchIndexList;
    private int size;
    private String word;

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public interface OnLongItemClickListener{
        void onLongItemClick(View v, int position);
    }

    public interface deleteListener{
        void onDeleteClick(View v, int position);
    }

    private CustomRecyclerAdatper.OnItemClickListener mListener = null;

    public void setOnItemClickListener(CustomRecyclerAdatper.OnItemClickListener listener){
        this.mListener = listener;
    }

    private CustomRecyclerAdatper.OnLongItemClickListener longListener = null;

    public void setLongItemClickListener(CustomRecyclerAdatper.OnLongItemClickListener listener){
        this.longListener = listener;
    }

    private CustomRecyclerAdatper.deleteListener dListener = null;

    public void setDeleteItemListener(CustomRecyclerAdatper.deleteListener listener){
        this.dListener = listener;
    }

    public CustomRecyclerAdatper(Context context, Activity activity, ArrayList<MyListItem> myListItem
            , ArrayList<Integer> searchIndexList, int size, String word) {
        this.context = context;
        this.activity = activity;
        this.myListItem = myListItem;
        this.searchIndexList = searchIndexList;
        this.size = size;
        this.word = word;
    }

    public CustomRecyclerAdatper(Context context, Activity activity
            , ArrayList<MyListItem> myListItem, int size, String word) {
        this.context = context;
        this.activity = activity;
        this.myListItem = myListItem;
        this.size = size;
        this.word = word;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(myListItem.get(position).getList().get(0).isSelected()){
            Log.v("1111111?","");
            holder.binding.ivDelete.setVisibility(View.VISIBLE);
        } else {
            Log.v("222222222?",""+myListItem.get(position).getList().get(0).isSelected());
            holder.binding.ivDelete.setVisibility(View.GONE);
        }

        if(word.equals("")) {
            wordEmpty(holder,position);
        } else {
            notWordEmpty(holder,position);
        }

    }

    /**
     * @DESC: 검색창이 빈 경우
     * @param holder
     * @param position
     */
    private void wordEmpty(ViewHolder holder, int position){
        Uri imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                + myListItem.get(position).getList().get(0).getUri());
        Glide.with(context).load(imagePath).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(myListItem.get(position).getList().get(0).getTitle());
        holder.binding.tvDescribe.setText(myListItem.get(position).getList().get(0).getDescribe());
    }


    /**
     * @DESC: 검색창에 단어가 있는경우
     * @param holder
     * @param position
     */
    private void notWordEmpty(ViewHolder holder, int position){
        Uri imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                + myListItem.get(searchIndexList.get(position)).getList().get(0).getUri());
        Glide.with(context).load(imagePath).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(myListItem.get(searchIndexList.get(position)).getList().get(0).getTitle());
        holder.binding.tvDescribe.setText(myListItem.get(searchIndexList.get(position)).getList().get(0).getDescribe());
    }


    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerViewListBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            initBinding();

            itemClick(itemView);

            longItemClick(itemView);

        }

        /**
         * @DESC: 바인딩
         */
        private void initBinding(){
            binding = RecyclerViewListBinding.bind(itemView);
        }

        /**
         * @DESC: 클릭 이벤트
         * @param itemView
         */
        private void itemClick(View itemView){
            itemView.setOnClickListener(v->{
                int pos = getBindingAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    mListener.onItemClick(v,pos);
                    dListener.onDeleteClick(v,pos);
                }
            });
        }

        private void longItemClick(View itemView){
            itemView.setOnLongClickListener(v->{
                int pos = getBindingAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    longListener.onLongItemClick(v,pos);
                    return true;
                }
                return false;
            });
        }

    }
}
