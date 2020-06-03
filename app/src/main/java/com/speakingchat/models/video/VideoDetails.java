package com.speakingchat.models.video;

import com.google.gson.annotations.SerializedName;

class VideoDetails {

    @SerializedName("statistics")
    private Statistics mStatistics;
    @SerializedName("liveStreamingDetails")
    private LiveStreamingDetails mLiveStreamingDetails;

    public Statistics getStatistics() {
        return mStatistics;
    }

    public LiveStreamingDetails getLiveStreamingDetails() {
        return mLiveStreamingDetails;
    }

    private class Statistics {

        @SerializedName("likeCount")
        private Long mLikeCount;
        @SerializedName("dislikeCount")
        private Long mDislikeCount;

        public Long getLikeCount() {
            return mLikeCount;
        }

        public Long getDislikeCount() {
            return mDislikeCount;
        }
    }

    private class LiveStreamingDetails {

        @SerializedName("concurrentViewers")
        private Long mConcurrentViewers;

        public Long getConcurrentViewers() {
            return mConcurrentViewers;
        }
    }
}
