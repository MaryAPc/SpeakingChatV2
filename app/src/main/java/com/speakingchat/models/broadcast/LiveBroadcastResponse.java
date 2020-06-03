package com.speakingchat.models.broadcast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveBroadcastResponse {

    @SerializedName("items")
    private List<ItemsBroadcast> mItemsBroadcasts;

    public List<ItemsBroadcast> getItemsBroadcasts() {
        return mItemsBroadcasts;
    }
}
