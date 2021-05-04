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
    public static final String KEY          = "&key=키값을 입력하세요";
    public static final String sch          = "search?";
    public static final String mx           = "&maxResults=50";
    public static final String part         = "&part=snippet";
    public static final String safe         = "&safeSearch=strict";
    public static final String type         = "&type=video";
    public static final String embeddable   = "&videoEmbeddable=true";
    public static final String query        = "&q=IU";
    public static final String chid         = "&channelID=UCweOkPb1wVVH0Q0Tlj4a5Pw";
    public static final String ord          = "&order=date";

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


/*
  s_url = "https://www.googleapis.com/youtube/v3/search?"
                        //+ "part=contentDetails"  //이거는 안됨 video 쿼리에서 가능한거임.
                        + "part=snippet"
                        + "&type=video"
                        //+ "&q=" + edt_search.getText().toString()
                        + "&q=" + URLEncoder.encode(lastEditSearchKeyword, "UTF-8")
                        + "&key="+ serverKey
                        + "&maxResults=50"
                        + "&safeSearch=strict"
                        + "&videoEmbeddable=true"  //아이유 팔레트로 검색했을때, 스브스 케이팝 꺼가 리스트에 안나오면 성공!
                        + "&pageToken=" + nextPageToken
                        //+ "&order=viewCount"
                        //+ "&videoCategoryID=music"  //이 옵션은 안먹힘.
                        //+ "&videoCategoryID=10"
                        ;
 */