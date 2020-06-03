package com.speakingchat.network;

import com.speakingchat.models.broadcast.LiveBroadcastResponse;
import com.speakingchat.models.chat.LiveChatResponse;
import com.speakingchat.models.video.VideoResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitApiInterface {

    @GET(GoogleApiUrls.Youtube.BROADCAST)
    Observable<LiveBroadcastResponse> getBroadcast(@Header("Authorization") String token, @Query("part") String part, @Query("broadcastStatus") String status, @Query("broadcastType") String type);

    @GET(GoogleApiUrls.Youtube.CHAT)
    Observable<LiveChatResponse> getChat(@Header("Authorization") String token, @Query("liveChatId") String liveChatId, @Query("part") String part);

    @GET(GoogleApiUrls.Youtube.CHAT)
    Observable<LiveChatResponse> getNextChatMessage(@Header("Authorization") String token, @Query("liveChatId") String liveChatId, @Query("part") String part, @Query("pageToken") String nextPageToken);

    @GET(GoogleApiUrls.Youtube.VIDEOS)
    Observable<VideoResponse> getVideo(@Header("Authorization") String token, @Query("part") String part, @Query("id") String videoId);

    /*@GET(GoogleApiUrls.OAuth.TOKEN_INFO_URL)
    Call<TokenResponse> getTokenInfo(@Query("access_token") String accessToken);*/
}
