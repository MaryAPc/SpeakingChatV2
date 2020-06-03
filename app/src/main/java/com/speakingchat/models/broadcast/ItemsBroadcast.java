package com.speakingchat.models.broadcast;

import com.google.gson.annotations.SerializedName;

public class ItemsBroadcast {

    @SerializedName("id")
    private String mIdBroadcast;
    @SerializedName("snippet")
    private Snippet mSnippet;

    //id трансляции
    public String getId() {
        return mIdBroadcast;
    }

    public Snippet getSnippet() {
        return mSnippet;
    }


    private static class Snippet {

        @SerializedName("channelId") //id канала
        private String mChannelId;
        @SerializedName("title") //название трансляции
        private String mTitle;
        @SerializedName("liveChatId") //id чата (для запроса)
        private String mLiveChatId;

        public String getChannelId() {
            return mChannelId;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getLiveChatId() {
            return mLiveChatId;
        }
    }
}
