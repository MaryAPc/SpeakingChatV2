package com.speakingchat.models.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {

    @SerializedName("items")
    private List<VideoDetails> mVideoDetails;

    public List<VideoDetails> getVideoDetails() {
        return mVideoDetails;
    }
}
