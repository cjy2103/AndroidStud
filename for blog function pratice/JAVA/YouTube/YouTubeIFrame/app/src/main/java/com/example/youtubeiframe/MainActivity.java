package com.example.youtubeiframe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.example.youtubeiframe.databinding.ActivityMainBinding;
import com.example.youtubeiframe.util.FormatUtil;
import com.example.youtubeiframe.util.LogUtil;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
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
    private boolean firstLoad = true;


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
        youTubePlayerView = binding.youtubePlayerView;
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerSeekBar = binding.youtubePlayerSeekbar;

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                mYouTubePlayer = youTubePlayer;
                youTubePlayer.cueVideo(videoId,0);
                youTubePlayer.addListener(youTubePlayerSeekBar);
            }

            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
                String currentTime = FormatUtil.convertSecondsToMinutesAndSeconds((int) second);
                binding.tvStartTime.setText(currentTime);
                binding.seekBar.setProgress((int) second);
            }

            @Override
            public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float duration) {
                if(firstLoad){
                   firstLoad = false;
                    String fullTime = FormatUtil.convertSecondsToMinutesAndSeconds(duration);
                    binding.tvEndTime.setText(fullTime);
                    binding.seekBar.setMax((int) duration);
                    binding.seekBar.setVisibility(View.VISIBLE);
                    binding.seekBar.getThumb().mutate().setAlpha(0);
                    seekbarListener();
                }
            }


        });

        youTubePlayerSeekBar.setYoutubePlayerSeekBarListener(time -> mYouTubePlayer.seekTo(time));

    }

    private void seekbarListener(){
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String currentTime = FormatUtil.convertSecondsToMinutesAndSeconds(progress);
                binding.tvStartTime.setText(currentTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                binding.seekBar.setScaleY(3);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mYouTubePlayer.seekTo(seekBar.getProgress());
                binding.seekBar.setScaleY(1);
            }
        });
    }




}