package com.example.youtubeplayerview;

import android.os.Bundle;
import android.util.Log;

import com.example.youtubeplayerview.databinding.ActivityMainBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class MainActivity extends YouTubeBaseActivity {

    private ActivityMainBinding binding;
    private String videoId;
    private int lastYoutubeSeekTime;
    private YouTubePlayer mYouTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        youtubeinit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mYouTubePlayer!=null) {
            lastYoutubeSeekTime = mYouTubePlayer.getCurrentTimeMillis();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mYouTubePlayer!=null) {
            mYouTubePlayer.cueVideo(videoId);
        }
    }

    /**
     * @DESC: 뷰바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        videoId = "b7kwTlwD1m0";
    }

    /**
     * @DESC: 유튜브 초기화
     */
    private void youtubeinit(){

        binding.youtubePlayerView.initialize("Test", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(youTubePlayer!=null){
                    if(!b) {
                        try {
                            youTubePlayer.cueVideo(videoId, lastYoutubeSeekTime);

                        } catch (IllegalStateException e) {
                            youtubeinit();
                        }
                    }
                }

                assert youTubePlayer != null;
                youTubePlayer.setShowFullscreenButton(false);
                mYouTubePlayer = youTubePlayer;
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                youtubeListener(youTubePlayer);

                youtubeEventListener(youTubePlayer);


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    /**
     * @DESC: 유튜브 비디오 상태 리스너
     * @param youTubePlayer
     */
    private void youtubeListener(YouTubePlayer youTubePlayer){
        youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

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
    }

    /**
     * @DESC: 유튜브 비디오 이벤트 리스너
     * @param youTubePlayer
     */
    private void youtubeEventListener(YouTubePlayer youTubePlayer){
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
}