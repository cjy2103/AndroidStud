package com.example.youtubetest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubetest.Adapter.MyAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class YouTubePlay extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    Button btn;
    YouTubePlayer.OnInitializedListener listener;
    YouTubePlayer mYouTubePlayer;
    String videoId = "0-q1KafFCLU";

    /**
     * 테스트 부분
     */
    private RecyclerView listView;
    private MyAdapter adapter;
    private int minute;
    private int fullTime;

    private static final String TAG = YouTubePlay.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //btn = findViewById(R.id.playBtn);
        youTubePlayerView = findViewById(R.id.youtubeView);

        youTubePlayerView.initialize("develop", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.v(TAG,"유튜브 초기화 성공");
                if(!b){
                    youTubePlayer.cueVideo(videoId);
                }

                youTubePlayer.setShowFullscreenButton(false);
                mYouTubePlayer = youTubePlayer;
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT); // 모든 기능 다있음

                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                        Log.v(TAG,"유튜브 상태 리스너 - onLoading");


                    }

                    @Override
                    public void onLoaded(String s) {
                        Log.v(TAG,"플레이어에서 가져온 노래의 시간은? : " + mYouTubePlayer.getDurationMillis());

                        fullTime = mYouTubePlayer.getDurationMillis()/1000;
                        Log.v(TAG,"새로구한 전체 시간은? : "+fullTime);

                        init();
                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {

                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });

                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {

                    }

                    @Override
                    public void onPaused() {

                    }

                    @Override
                    public void onStopped() {

                    }

                    @Override
                    public void onBuffering(boolean b) {

                    }

                    @Override
                    public void onSeekTo(int i) {

                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        /***
         * 기존 소스
         */
//        listener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo("0-q1KafFCLU");
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        };





        /***
         * 기존 소스
         */
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                youTubePlayerView.initialize("AIzaSyCrQkBhJyvylYUc4wTJE6naXYsQ9-YqiVk",listener);
//
////                int fulltime = mYouTubePlayer.getDurationMillis()/1000;
////
////                Log.v(TAG,"이거 될까? fulltime : " + fulltime);
//            }
//        });




    }

    private void init() {
        listView = findViewById(R.id.main_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);
//        int fulltime = mYouTubePlayer.getDurationMillis()/1000;
        //int temp = Integer.parseInt(duration);
        Log.v(TAG,"duration 값은 : "+mYouTubePlayer);
        Log.v(TAG,"여기서 fulltime값은? : "+fullTime);
        ArrayList<String> itemList = new ArrayList<>();
        for(int i=1;i<=fullTime;i++){
            if(i<10) {
                itemList.add("0"+String.valueOf(i));
            }
            else if(i>59){
                minute = i/60;
                if((i%60) <10) {
                    itemList.add("0" + String.valueOf(minute) + ": 0"+String.valueOf(i-60*minute));
                }
                else{
                    itemList.add("0" + String.valueOf(minute) + ": "+String.valueOf(i-60*minute));
                }
            }
            else{
                itemList.add(String.valueOf(i));
            }
        }


        adapter = new MyAdapter(itemList, this, onClickItem);
        listView.setAdapter(adapter);

        MyListDecoration decoration = new MyListDecoration();
        listView.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(YouTubePlay.this, str, Toast.LENGTH_SHORT).show();
        }
    };

}
