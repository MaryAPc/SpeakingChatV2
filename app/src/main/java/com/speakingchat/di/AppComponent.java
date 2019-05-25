package com.speakingchat.di;


import com.speakingchat.di.module.AppContextModule;
import com.speakingchat.di.module.PreferencesModule;
import com.speakingchat.views.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppContextModule.class, PreferencesModule.class})
public interface AppComponent {

	void inject(MainActivity activity);
}
