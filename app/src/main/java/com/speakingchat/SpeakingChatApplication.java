package com.speakingchat;

import android.app.Application;

import com.speakingchat.di.components.AppComponent;
import com.speakingchat.di.components.DaggerAppComponent;
import com.speakingchat.di.module.AppContextModule;
import com.speakingchat.di.module.PreferencesModule;
import com.speakingchat.di.module.RetrofitModule;
import com.speakingchat.di.module.RxEventBusModule;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class SpeakingChatApplication extends Application {

    private static AppComponent sAppComponent;
    public static SpeakingChatApplication sInstance;
    private Cicerone<Router> mCicerone;

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

        mCicerone = Cicerone.create();
    }

    public static SpeakingChatApplication getInstance() {
        return sInstance;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public NavigatorHolder getNavigatorHolder() {
        return mCicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return mCicerone.getRouter();
    }
}
