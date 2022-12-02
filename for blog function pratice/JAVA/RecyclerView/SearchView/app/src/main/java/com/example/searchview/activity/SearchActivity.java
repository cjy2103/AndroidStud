package com.example.searchview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.example.searchview.adapter.SearchHistoryAdapter;
import com.example.searchview.databinding.ActivitySearchBinding;
import com.example.searchview.model.SearchModel;
import com.example.searchview.util.SystemUtil;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private SearchModel searchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        loadHistory();
        clickEvent();
        searchWordSending();
    }

    private void viewBinding(){
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        searchModel = new SearchModel(this,this);
        binding.recyclerHistory.setLayoutManager(new LinearLayoutManager(this));

        SystemUtil.sofNavigationBarHide(getWindow());
        SystemUtil.statusbarSetting(getWindow(),this,binding.consSearch);
    }

    private void loadHistory(){
        searchModel.loadHistory();
    }

    private void clickEvent(){
        clickBack();
        clickClearAll();
    }

    private void clickBack(){
        binding.iBtnBack.setOnClickListener(v-> finish());
    }

    private void searchWordSending(){
        binding.edtWord.setOnEditorActionListener((v, actionId, event) -> {
            String word = binding.edtWord.getText().toString();
            searchModel.sendWord(word,actionId);
            return true;
        });
    }

    public void showText(){
        binding.tvNotHistory.setVisibility(View.VISIBLE);
    }

    public void hideText(){
        binding.tvNotHistory.setVisibility(View.GONE);
    }

    public void recyclerViewConnection(SearchHistoryAdapter adapter){
        binding.recyclerHistory.setAdapter(adapter);
    }

    private void clickClearAll(){
        binding.tvAllDelete.setOnClickListener(v->{
            searchModel.deleteAll();
        });
    }

}