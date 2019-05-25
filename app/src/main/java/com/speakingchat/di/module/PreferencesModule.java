package com.speakingchat.di.module;

import android.content.Context;

import com.speakingchat.utils.AppPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

	@Provides
	@Singleton
	AppPreferences providesAppPreferences(Context context) {
		return new AppPreferences(context);
	}
}
