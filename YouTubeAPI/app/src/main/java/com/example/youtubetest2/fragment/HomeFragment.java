package com.example.youtubetest2.fragment;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.youtubetest2.R;
import com.example.youtubetest2.adapter.AdapterHome;
import com.example.youtubetest2.models.ModelHome;
import com.example.youtubetest2.models.VideoYT;
import com.example.youtubetest2.network.YoutubeAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        RecyclerView rv = view.findViewById(R.id.recyclerView);
        adapter         = new AdapterHome(getContext(), videoList);
        manager         = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        if (videoList.size() == 0){
            getJson();
        }

        return view;
    }

    private void getJson() {
        String url = YoutubeAPI.BASC_URL + YoutubeAPI.sch + YoutubeAPI.part + YoutubeAPI.type
                + YoutubeAPI.query + YoutubeAPI.KEY  + YoutubeAPI.mx + YoutubeAPI.safe + YoutubeAPI.embeddable;
//                String url = YoutubeAPI.BASC_URL + YoutubeAPI.sch + YoutubeAPI.KEY + YoutubeAPI.chid + YoutubeAPI.mx
//                        +YoutubeAPI.ord+YoutubeAPI.part;
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if (response.errorBody() != null){
                    try {
                        Log.v(TAG , "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.v(TAG,  "body: "+response.body().getItems());
                    ModelHome mh = response.body();
                    videoList.addAll(mh.getItems());

//                    List<VideoYT> videoList = mh.getItems();
//                    adapter.setVideoList(videoList);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
            }
        });
    }
}