package com.speakingchat.di.module;

import android.content.Context;

import com.speakingchat.di.annotations.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {
	private Context context;

	public AppContextModule(Context context) {
		this.context = context;
	}

	@Provides
	@ApplicationScope
	Context providesContext() {
		return context;
	}
}

