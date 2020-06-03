package com.speakingchat.models;

public class User {

    private String mAvatar;
    private String mName;
    private String mEmail;

    public User(String avatar, String name, String email) {
        mAvatar = avatar;
        mName = name;
        mEmail = email;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }
}
