package com.speakingchat.models.chat;

import com.google.gson.annotations.SerializedName;

public class ItemsChat {

    @SerializedName("snippet")
    private Snippet mSnippet;
    @SerializedName("authorDetails")
    private AuthorDetails mAuthorDetails;

    public Snippet getSnippet() {
        return mSnippet;
    }

    public AuthorDetails getAuthorDetails() {
        return mAuthorDetails;
    }

    private static class Snippet {
        @SerializedName("type")
        private ChatEventType mChatEventType;
        @SerializedName("textMessageDetails")
        private TextMessageDetails mTextMessageDetails;
        @SerializedName("superChatDetails")
        private SuperChatDetails mSuperChatDetails;

        public TextMessageDetails getTextMessageDetails() {
            return mTextMessageDetails;
        }

        public ChatEventType getChatEventType() {
            return mChatEventType;
        }

        public SuperChatDetails getSuperChatDetails() {
            return mSuperChatDetails;
        }
    }

    private static class TextMessageDetails {
        @SerializedName("messageText")
        private String mMessageText;

        public String getMessageText() {
            return mMessageText;
        }
    }

    private static class SuperChatDetails {
        @SerializedName("amountDisplayString")
        private String mAmountDisplayString;
        @SerializedName("userComment")
        private String mUserComment;

        public String getAmountDisplayString() {
            return mAmountDisplayString;
        }

        public String getUserComment() {
            return mUserComment;
        }
    }

    private static class AuthorDetails {
        @SerializedName("displayName")
        private String mDisplayName;
        @SerializedName("profileImageUrl")
        private String mProfileImage;

        public String getProfileImage() {
            return mProfileImage;
        }

        public String getDisplayName() {
            return mDisplayName;
        }
    }

    private enum ChatEventType {
        SUPER_CHAT_EVENT("superChatEvent"),
        TEXT_MESSAGE_EVENT("textMessageEvent");

        public final String label;

        ChatEventType(String label) {
            this.label = label;
        }
    }
}
