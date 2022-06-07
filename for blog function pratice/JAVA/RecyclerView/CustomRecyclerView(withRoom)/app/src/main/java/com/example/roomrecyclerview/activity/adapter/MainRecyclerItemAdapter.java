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
import com.example.roomrecyclerview.activity.model.MyListItem;
import com.example.roomrecyclerview.databinding.RecyclerViewListBinding;
import com.example.roomrecyclerview.util.LanguageCheck;

import java.util.ArrayList;

public class MainRecyclerItemAdapter extends RecyclerView.Adapter<MainRecyclerItemAdapter.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList<MyListItem> listItems;
    private Typeface tfRoboto;
    private Typeface tfMaple;
    private LanguageCheck languageCheck = new LanguageCheck();

    public MainRecyclerItemAdapter(Context context, Activity activity, ArrayList<MyListItem> listItems) {
        this.context = context;
        this.activity = activity;
        this.listItems = listItems;

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
        languageCheck.checkLanguage(holder.binding.tvTitle, tfRoboto, tfMaple, 26, 24);

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
