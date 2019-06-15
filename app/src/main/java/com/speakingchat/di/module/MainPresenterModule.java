package com.speakingchat.di.module;

import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.presenters.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    @Provides
    @ApplicationScope
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
