package com.example.youtubetest2.network;

import com.example.youtubetest2.models.ModelHome;

import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {

    public static final String BASC_URL     = "https://www.googleapis.com/youtube/v3/";
    public static final String sch          = "search?";

    public static final String KEY          = "&key=개인 키 입력";


    public static final String mx           = "&maxResults=50";
    public static final String part         = "&part=snippet";
    public static final String safe         = "&safeSearch=strict";

    public static final String embeddable   = "&videoEmbeddable=true";
    public static final String query        = "&q=IU";
    //public static final String ord          = "&order=date";
    public static final String ord          = "&order=relevance";

    public static final String Searchquery  = "&q=";
    public static final String type         = "&type=video";

    public interface HomeVideo {
        @GET
        Call<ModelHome> getYT(@Url String url);
    }

    public static HomeVideo homeVideo = null;

    public static HomeVideo getHomeVideo(){
        if(homeVideo == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASC_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            homeVideo = retrofit.create(HomeVideo.class);
        }
        return homeVideo;
    }




}
