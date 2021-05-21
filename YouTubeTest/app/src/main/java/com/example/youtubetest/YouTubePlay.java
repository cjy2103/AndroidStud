package com.example.youtubetest;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubetest.Adapter.MyAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class YouTubePlay extends YouTubeBaseActivity {

    /****************************************************************************************************
     ***************************************** 변수 선언단 **********************************************
     ***************************************************************************************************/

    private static final String TAG = YouTubePlay.class.getSimpleName();

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer mYouTubePlayer;
    String videoId = "0-q1KafFCLU";


    private RecyclerView listView;
    private MyAdapter adapter;
    private int minute;
    private int fullTime;

    private boolean moveLineFinish = false;





    /****************************************************************************************************
     ******************************************** onCreate **********************************************
     ***************************************************************************************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        init();

    }



    /****************************************************************************************************
     **************************************** 뷰 이벤트 영역 *******************************************
     ***************************************************************************************************/

    /**
     * @DESC 빨간선 이동 시키는 함수
     */
    private void startMove(){
        View redLine = (View) findViewById(R.id.red_line);

        redLine.setVisibility(View.VISIBLE);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_anim);
        redLine.startAnimation(anim);

        if(moveLineFinish){
            moveView();
        }

    }
    

    /**
     * @DESC 화면을 오른쪽으로 이동시키는 함수
     */
    private void moveView(){
        RecyclerView listView = (RecyclerView) findViewById(R.id.main_listview);


    }

    /***************************************************************************************************
     ****************************************** 내부 클래스 ********************************************
     **************************************************************************************************/

    /**
     * @DESC : 선 이동 이벤트가 발생했을때 애니메이션이 종료되는 시점을 감지하기 위해 사용
     */
    private class MoveLine implements Animation.AnimationListener{


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            moveLineFinish = true;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }




    /****************************************************************************************************
     **************************************** 사용자 정의 함수 ******************************************
     ***************************************************************************************************/

    /**
     * @DESC 유튜브 초기화 하는 부분 ( 유튜브 동영상 로드 하는 부분 )
     */
    private void init(){
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

                        durationLoad();
                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {
                        startMove();
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
    }



    /**
     * @DESC 영상 길이별 시간 설정해서 리사이클러 뷰에 넣는 함수
     */
    private void durationLoad() {
        listView = findViewById(R.id.main_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);
        
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


        adapter = new MyAdapter(itemList,this);

        listView.setAdapter(adapter);



    }






}
