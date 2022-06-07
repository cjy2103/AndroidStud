package com.example.roomrecyclerview.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.model.MyListItem;
import com.example.roomrecyclerview.databinding.RecyclerViewListBinding;
import com.example.roomrecyclerview.util.LanguageCheck;

import java.util.ArrayList;

public class MainRecyclerItemAdapter extends RecyclerView.Adapter<MainRecyclerItemAdapter.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<MyListItem> listItems;
    private int size;
    private String word;
    private ArrayList<Integer> searchIndex;

    private Typeface tfRoboto;
    private Typeface tfMaple;
    private LanguageCheck languageCheck = new LanguageCheck();

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private MainRecyclerItemAdapter.OnItemClickListener mListener = null;

    public void setOnItemClickListener(MainRecyclerItemAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }

    public MainRecyclerItemAdapter(Context context, Activity activity, ArrayList<MyListItem> listItems, int size, String word, ArrayList<Integer> searchIndex) {
        this.context = context;
        this.activity = activity;
        this.listItems = listItems;
        this.size = size;
        this.word = word;
        this.searchIndex = searchIndex;

        tfRoboto = context.getResources().getFont(R.font.font_english);
        tfMaple = context.getResources().getFont(R.font.font_korean);
    }

    public MainRecyclerItemAdapter(Context context, Activity activity, ArrayList<MyListItem> listItems, int size, String word) {
        this.context = context;
        this.activity = activity;
        this.listItems = listItems;
        this.size = size;
        this.word = word;

        tfRoboto = context.getResources().getFont(R.font.font_english);
        tfMaple = context.getResources().getFont(R.font.font_korean);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(word.equals("")){
            wordEmpty(holder, position);
        } else {
            notWordEmpty(holder, position);
        }
    }

    private void wordEmpty(ViewHolder holder, int position){
        Uri imagePath;
        if(listItems.get(position).getList().get(0).getImageCase().equals("Local")){
            imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                    + listItems.get(position).getList().get(0).getUri());
        } else {
            imagePath =  Uri.parse(listItems.get(position).getList().get(0).getUri());
        }

        Glide.with(context).load(imagePath).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(listItems.get(position).getList().get(0).getTitle());
        languageCheck.checkLanguage(holder.binding.tvTitle, tfRoboto, tfMaple, 26, 24);

        holder.binding.tvDescribe.setText(listItems.get(position).getList().get(0).getDescribe());
        languageCheck.checkLanguage(holder.binding.tvDescribe, tfRoboto, tfMaple, 20, 18);
    }

    private void notWordEmpty(ViewHolder holder, int position){
        Uri imagePath;
        if(listItems.get(searchIndex.get(position)).getList().get(0).getImageCase().equals("Local")){
            imagePath = Uri.parse("android.resource://" + activity.getPackageName() + "/"
                    + listItems.get(searchIndex.get(position)).getList().get(0).getUri());
        } else {
            imagePath =  Uri.parse(listItems.get(searchIndex.get(position)).getList().get(0).getUri());
        }

        Glide.with(context).load(imagePath).into(holder.binding.ivArt);
        holder.binding.tvTitle.setText(listItems.get(searchIndex.get(position)).getList().get(0).getTitle());
        languageCheck.checkLanguage(holder.binding.tvTitle, tfRoboto, tfMaple, 26, 24);

        holder.binding.tvDescribe.setText(listItems.get(searchIndex.get(position)).getList().get(0).getDescribe());
        languageCheck.checkLanguage(holder.binding.tvDescribe, tfRoboto, tfMaple, 20, 18);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerViewListBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            viewBinding();
            itemClick();
        }

        private void viewBinding(){
            binding = RecyclerViewListBinding.bind(itemView);
        }

        private void itemClick(){
            itemView.setOnClickListener(v->{
                int pos = getBindingAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    mListener.onItemClick(v,pos);
                }
            });
        }
    }
}
