package com.example.youtubetest2.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.youtubetest2.R;
import com.example.youtubetest2.adapter.AdapterHome;
import com.example.youtubetest2.models.ModelHome;
import com.example.youtubetest2.models.VideoYT;
import com.example.youtubetest2.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private EditText input_query;
    private Button btn_search;
    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Inflate the layout for this fragment
        input_query = view.findViewById(R.id.input_query);
        btn_search = view.findViewById(R.id.btn_serach);
        RecyclerView rv = view.findViewById(R.id.recycler_search);

        adapter = new AdapterHome(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(input_query.getText().toString())){
                    getJson(input_query.getText().toString());
                }
                else{
                    Toast.makeText(getContext(), "검색어를 입력해주시기를 바랍니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void getJson(String toString) {
        String url = YoutubeAPI.BASC_URL + YoutubeAPI.sch + YoutubeAPI.part + YoutubeAPI.type
                + YoutubeAPI.Searchquery + toString + YoutubeAPI.KEY  + YoutubeAPI.mx + YoutubeAPI.safe + YoutubeAPI.embeddable
                + YoutubeAPI.ord;

        /*
        String url = YoutubeAPI.sch + YoutubeAPI.part + YoutubeAPI.type
                + YoutubeAPI.Searchquery + toString + YoutubeAPI.KEY  + YoutubeAPI.mx + YoutubeAPI.safe + YoutubeAPI.embeddable
                + YoutubeAPI.ord;
        */

        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if(response.errorBody() != null){
                    Log.v(TAG,"onResponse search : " + response.errorBody().toString());
                }
                else {
                    ModelHome mh = response.body();
                    if (mh.getItems().size() != 0){
                        videoList.addAll(mh.getItems());
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getContext(), "검색 결과 없음", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.v(TAG, "onFailure search :" + t);

            }
        });


    }
}