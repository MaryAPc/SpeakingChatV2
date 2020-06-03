package com.speakingchat.di.module;

import android.content.Context;

import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.utils.AppPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

	@Provides
	@ApplicationScope
	AppPreferences providesAppPreferences(Context context) {
		return new AppPreferences(context);
	}
}
