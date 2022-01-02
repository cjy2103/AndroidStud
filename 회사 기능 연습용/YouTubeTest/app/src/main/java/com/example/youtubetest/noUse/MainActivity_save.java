package com.example.youtubetest.noUse;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class MainActivity_save extends YouTubeBaseActivity {

//    private String API_KEY = "키값";
//
//    private TextView tv_result;
//    private String result;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        tv_result = findViewById(R.id.tv_result);
//
//        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                YoutubeAsyncTask youtubeAsyncTask = new YoutubeAsyncTask();
//                youtubeAsyncTask.execute();
//            }
//        });
//
//        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tv_result.setText(result);
//
//            }
//        });
//    }
//
//    private class YoutubeAsyncTask extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//                HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//                final JsonFactory JSON_FACTORY = new JacksonFactory();
//                final long NUMBER_OF_VIDEOS_RETURNED = 5;
//
//                YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
//                    public void initialize(HttpRequest request) throws IOException {
//                    }
//                }).setApplicationName("youtube-search-sample").build();
//
//                YouTube.Search.List search = youtube.search().list("id,snippet");
//
//                search.setKey(API_KEY);
//
//                search.setQ("아이린");
//                search.setChannelId("UCk9GmdlDTBfgGRb7vXeRMoQ"); //레드벨벳 공식 유투브 채널
//                search.setOrder("relevance"); //date relevance
//
//                search.setType("video");
//
//                search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//                SearchListResponse searchResponse = search.execute();
//
//                List<SearchResult> searchResultList = searchResponse.getItems();
//
//                if (searchResultList != null) {
//                    prettyPrint(searchResultList.iterator(), "레드벨벳");
//                }
//            } catch (GoogleJsonResponseException e) {
//                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                        + e.getDetails().getMessage());
//                System.err.println("There was a service error 2: " + e.getLocalizedMessage() + " , " + e.toString());
//            } catch (IOException e) {
//                System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//            } catch (Throwable t) {
//                t.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//
//        public void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
//            if (!iteratorSearchResults.hasNext()) {
//                System.out.println(" There aren't any results for your query.");
//            }
//
//            StringBuilder sb = new StringBuilder();
//
//            while (iteratorSearchResults.hasNext()) {
//                SearchResult singleVideo = iteratorSearchResults.next();
//                ResourceId rId = singleVideo.getId();
//
//                // Double checks the kind is video.
//                if (rId.getKind().equals("youtube#video")) {
//                    Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
//                    sb.append("ID : " + rId.getVideoId() + " , 제목 : " + singleVideo.getSnippet().getTitle() + " , 썸네일 주소 : " + thumbnail.getUrl());
//                    sb.append("\n");
//                }
//            }
//
//            result = sb.toString();
//        }
//    }





}


