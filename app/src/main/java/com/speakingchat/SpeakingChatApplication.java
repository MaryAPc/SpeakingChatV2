package com.speakingchat;

import android.app.Application;

import com.speakingchat.di.components.AppComponent;
import com.speakingchat.di.components.DaggerAppComponent;
import com.speakingchat.di.module.AppContextModule;
import com.speakingchat.di.module.PreferencesModule;
import com.speakingchat.di.module.RetrofitModule;
import com.speakingchat.di.module.RxEventBusModule;

public class SpeakingChatApplication extends Application {

    private static AppComponent sAppComponent;
    public static SpeakingChatApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        sInstance = this;

        sAppComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .preferencesModule(new PreferencesModule())
                .retrofitModule(new RetrofitModule())
                .rxEventBusModule(new RxEventBusModule())
                .build();
    }

    public static SpeakingChatApplication getInstance() {
        return sInstance;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
