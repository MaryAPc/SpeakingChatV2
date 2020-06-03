package com.speakingchat.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private static final String APP_PREFERENCES = "speaking_app_prefs";
    private static final String SIGNED_IN = "signed_in";

    private final SharedPreferences mSharedPreferences;

    public AppPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean isSignedIn() {
        return mSharedPreferences.getBoolean(SIGNED_IN, false);
    }

    public void setSignedIn(boolean isSignedIn) {
        mSharedPreferences.edit().putBoolean(SIGNED_IN, isSignedIn).apply();
    }
}
