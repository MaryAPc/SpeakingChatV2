package com.speakingchat.di.module;


import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.network.GoogleApiUrls;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(GoogleApiUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
