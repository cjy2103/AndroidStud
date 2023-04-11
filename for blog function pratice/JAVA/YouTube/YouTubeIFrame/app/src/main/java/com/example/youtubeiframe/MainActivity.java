package com.example.youtubeiframe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.youtubeiframe.databinding.ActivityMainBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private YouTubePlayerView youTubePlayerView;
    private String videoId = "";
    private YouTubePlayerSeekBar youTubePlayerSeekBar;
    private YouTubePlayer mYouTubePlayer;

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
        videoId = "gt-v_YCkaMY";
        youtubeInit();
    }



    private void youtubeInit(){
        // viewBinding을 쓰든 DataBinding을 쓰든 꼭 처리해 줘야하는 작업입니다.
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerSeekBar = binding.youtubePlayerSeekbar;

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                mYouTubePlayer = youTubePlayer;
                youTubePlayer.cueVideo(videoId,0);
                youTubePlayer.addListener(youTubePlayerSeekBar);
            }
        });

        youTubePlayerSeekBar.setYoutubePlayerSeekBarListener(time -> mYouTubePlayer.seekTo(time));

    }



}