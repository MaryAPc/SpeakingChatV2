package com.speakingchat.di.components;


import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.di.module.AppContextModule;
import com.speakingchat.di.module.GoogleApiClientModule;
import com.speakingchat.di.module.PreferencesModule;
import com.speakingchat.di.module.RetrofitModule;
import com.speakingchat.views.activities.MainActivity;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {AppContextModule.class, PreferencesModule.class, RetrofitModule.class})
public interface AppComponent {

	void inject(MainActivity activity);

	SignInComponent createSignInComponent(GoogleApiClientModule module);

	Retrofit getRetrofit();
}
