package com.example.roomrecyclerview.activity.model;

import java.io.Serializable;

public class ListItemModel implements Serializable{
    private String title;
    private String describe;
    private String uri;
    private String channelLink;
    private String imageCase;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }

    public String getImageCase() {
        return imageCase;
    }

    public void setImageCase(String imageCase) {
        this.imageCase = imageCase;
    }
}
