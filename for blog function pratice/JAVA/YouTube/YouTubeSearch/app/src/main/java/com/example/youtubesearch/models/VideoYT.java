package com.example.youtubesearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT {
    @SerializedName("id")
    @Expose
    private VideoID id;

    @SerializedName("snippet")
    @Expose
    private SnippetYT snippet;

}
