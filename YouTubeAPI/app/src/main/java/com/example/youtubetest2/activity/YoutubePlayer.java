package com.example.youtubetest2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.youtubetest2.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class YoutubePlayer extends AppCompatActivity {

    private YouTubePlayerView youtubePlayer;
    private TextView videoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        youtubePlayer   = findViewById(R.id.youtube_player);
        videoTitle      = findViewById(R.id.video_title);

        Intent data = getIntent();
        final String videoId = data.getStringExtra("video_id");
        String video_title   = data.getStringExtra("video_title");

        youtubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId,0);
            }
        });

        videoTitle.setText(video_title);
    }
}