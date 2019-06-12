package com.speakingchat.di.module;

import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.eventbus.RxEventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class RxEventBusModule {

    @Provides
    @ApplicationScope
    RxEventBus provideRxEventBus() {
        return new RxEventBus();
    }
}
