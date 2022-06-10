package com.example.youtubesearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnippetYT {

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnails")
    @Expose
    private TumbnailYT thumbnails;


}
