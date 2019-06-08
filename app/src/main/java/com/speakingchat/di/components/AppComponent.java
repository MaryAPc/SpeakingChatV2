package com.speakingchat.di.components;


import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.di.module.AppContextModule;
import com.speakingchat.di.module.GoogleApiClientModule;
import com.speakingchat.di.module.PreferencesModule;
import com.speakingchat.views.activities.MainActivity;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppContextModule.class, PreferencesModule.class})
public interface AppComponent {

	void inject(MainActivity activity);

	SignInComponent createSignInComponent(GoogleApiClientModule module);
}
