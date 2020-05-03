package com.raven.models;

import java.io.Serializable;

public class Video implements Serializable {

    public static final long serialVersionUID = 2019L;

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getChannalTitle() {
        return channalTitle;
    }

    public void setChannalTitle(String channalTitle) {
        this.channalTitle = channalTitle;
    }

    public Video() {
    }

    public Video(String videoID, String videoURL, String videoTitle, String thumbnailURL, String description, String duration, String channalTitle) {
        this.videoID = videoID;
        this.videoURL = videoURL;
        this.videoTitle = videoTitle;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.duration = duration;
        this.channalTitle = channalTitle;
    }

    private String videoID;
    private String videoURL;
    private String videoTitle;
    private String thumbnailURL;
    private String description;
    private String duration;
    private String channalTitle;

    public Video copy() {
        return new Video(videoID, videoURL, videoTitle, thumbnailURL, description, duration, channalTitle);
    }

}
