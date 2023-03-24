package com.example.listadapter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadapter.R;
import com.example.listadapter.databinding.RecyclerViewListBinding;
import com.example.listadapter.model.Character;
import com.example.listadapter.util.CharacterDiffUtil;


public class CharacterAdapter extends ListAdapter<Character, CharacterAdapter.ViewHolder> {

    public CharacterAdapter(){
        super(new CharacterDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recycler_view_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerViewListBinding binding;

        public ViewHolder(RecyclerViewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Character character){
            binding.setCharacter(character);
            binding.executePendingBindings();
        }
    }
}
