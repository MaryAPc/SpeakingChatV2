package com.speakingchat.network;

public class GoogleApiUrls {

    public static final String BASE_URL = "https://www.googleapis.com";
    static final String YOUTUBE_URL = BASE_URL + "/youtube/v3";
    static final String OAUTH_URL = BASE_URL + "/oauth2/v3";

    public static class Youtube {

        static final String CHAT = YOUTUBE_URL + "/liveChat/messages";
        static final String BROADCAST = YOUTUBE_URL + "/liveBroadcasts";
        static final String VIDEOS = YOUTUBE_URL + "/videos";
    }

    public static class OAuth {

        static final String TOKEN_INFO_URL =  OAUTH_URL + "/tokeninfo";
    }
}
