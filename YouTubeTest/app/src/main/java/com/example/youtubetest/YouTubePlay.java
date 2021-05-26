package com.example.youtubetest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
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


    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private int minute;
    private int fullTime;

    boolean youTubePlayerFlag = true;
    private int animCount = 0;
    int deCocount =0;




    /****************************************************************************************************
     ******************************************** onCreate **********************************************
     ***************************************************************************************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        recyclerView = findViewById(R.id.main_listview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        init();


    }


    /****************************************************************************************************
     **************************************** 뷰 이벤트 영역 *******************************************
     ***************************************************************************************************/

//    /**
//     * @DESC 빨간선 이동 시키는 함수
//     */
//    private void startMove(){
//
//
//
//        View redLine = (View) findViewById(R.id.red_line);
//
//        redLine.setVisibility(View.VISIBLE);
//
////        int layoutWidth = recyclerView.getWidth()/2;
////
////        ObjectAnimator anim = ObjectAnimator.ofFloat(redLine, View.TRANSLATION_X, 50f).setDuration(10000);
////        anim.setRepeatCount(Animation.INFINITE);
////        anim.setInterpolator(new LinearInterpolator());
////
////        new Animator.AnimatorListener() {
////            private String Tag;
////
////            @Override
////            public void onAnimationStart(Animator animation) {
////
////            }
////
////            @Override
////            public void onAnimationEnd(Animator animation) {
////                Log.v(Tag, "애니메이션이 종료되었다");
////                //moveView();
////                RecyclerViewMove nr = new RecyclerViewMove();
////                Thread t = new Thread(nr);
////                t.start();
////            }
////
////            @Override
////            public void onAnimationCancel(Animator animation) {
////
////            }
////
////            @Override
////            public void onAnimationRepeat(Animator animation) {
////
////            }
////        };
////
////        anim.start();
//
//
//
//        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_anim);
//
//        anim.setAnimationListener(new MoveLine());
//
//
//
//        //redLine.startAnimation(anim);
//        if(youTubePlayerFlag == true){
//            redLine.startAnimation(anim);
//            Log.v(TAG,"애니메이션 단계 여기는 트루임");
//        } else {
//            Log.v(TAG,"이거 일단 먹히나?");
//        }
//////         애니메이션이 종료되면 public void onAnimationEnd -> 애니메이션 종료 이벤트로 이동
//
//    }


    /***************************************************************************************************
     ****************************************** 내부 클래스 ********************************************
     **************************************************************************************************/

    /**
     * @DESC : 선 이동 이벤트가 발생했을때 애니메이션이 종료되는 시점을 감지하기 위해 사용
     */
    private class MoveLine implements Animation.AnimationListener{


        private String Tag = MoveLine.class.getSimpleName();;


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            
            Log.v(Tag, "애니메이션이 종료되었다");
            //moveView();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }


    }


    /**
     * @DESC 화면을 오른쪽으로 이동시키는 클래스
     */

    class RecyclerViewMove implements Runnable {
        RecyclerView rv = (RecyclerView) findViewById(R.id.main_listview);
        Handler handler = new Handler();

        @Override
        public void run() {
            while (true) {
                if(youTubePlayerFlag) {
                    recyclerView.scrollBy (1,0);

                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

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
                        //startMove();
                        RecyclerViewMove nr = new RecyclerViewMove();
                        Thread t = new Thread(nr);
                        t.start();

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
                        youTubePlayerFlag = true;

                    }

                    @Override
                    public void onPaused() {
                        youTubePlayerFlag = false;

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

        int recyclerViewWidth = recyclerView.getWidth()/2;

        if(deCocount == 0) {
            ArrayList<String> itemList = new ArrayList<>();
            recyclerView.addItemDecoration(new RecyclerViewItemDecoration(recyclerViewWidth));
            deCocount++;

            for(int i=0;i<=fullTime;i++){

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

            recyclerView.setAdapter(adapter);
        }
        Log.v(TAG,"내 길이는 이거야 :" +recyclerViewWidth);


    }


}
