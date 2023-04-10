package com.example.youtubeiframe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.youtubeiframe.databinding.ActivityMainBinding;
import com.example.youtubeiframe.util.LogUtil;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayerTracker tracker;
    private String videoId = "";
    private boolean firstLoad = false;
    private int fullTime = 0;
    private int currentTime = 0;

    private YouTubePlayerListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        videoId = "gt-v_YCkaMY"; // TODO : 바꾸세요~
        youtubeUiCustom();
        youtubeInit();
    }

    private void youtubeUiCustom(){
        listener = new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
            }
        };
    }


    private void youtubeInit(){
        // viewBinding을 쓰든 DataBinding을 쓰든 꼭 처리해 줘야하는 작업입니다.
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        tracker = new YouTubePlayerTracker();

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId,0);
                youTubePlayer.addListener(tracker);

            }

            @Override
            public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float duration) {
                if(!firstLoad){
                    fullTime = (int) duration;
                    String strFullTime = convertTimeFormat(fullTime);
                    binding.tvEndTime.setText(strFullTime);
                }
                currentTime = (int) tracker.getCurrentSecond();
                StringBuilder strCurrentTime = new StringBuilder(convertTimeFormat(currentTime));
                binding.tvCurrentTime.setText(strCurrentTime);

            }



            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
                currentTime = (int) second;
                StringBuilder strCurrentTime = new StringBuilder(convertTimeFormat(currentTime));
                binding.tvCurrentTime.setText(strCurrentTime);
            }
        });
    }

    private String convertTimeFormat(int seconds){
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds);
    }



}