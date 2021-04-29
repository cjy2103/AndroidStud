package com.example.youtubetest2.network;

public class YoutubeAPI {

    public static final String BASC_URL = "https://www.googleapis.com/youtube/v3/search";
    public static final String KEY      = "AIzaSyCrQkBhJyvylYUc4wTJE6naXYsQ9-YqiVk";
    public static final String sch      = "search?";

    


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