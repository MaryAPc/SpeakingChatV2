package com.speakingchat.models.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveChatResponse {

    @SerializedName("nextPageToken")
    private String mNextPageToken;
    @SerializedName("items")
    private List<ItemsChat> mItemsChat;
    @SerializedName("pollingIntervalMillis")
    private int mPollingIntervalMillis;

    public String getNextPageToken() {
        return mNextPageToken;
    }

    public List<ItemsChat> getItems() {
        return mItemsChat;
    }

    public int getPollingIntervalMillis() {
        return mPollingIntervalMillis;
    }

}
