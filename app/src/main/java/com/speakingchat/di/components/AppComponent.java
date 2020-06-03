package com.speakingchat.di.components;


import com.speakingchat.di.annotations.ApplicationScope;
import com.speakingchat.di.module.AppContextModule;
import com.speakingchat.di.module.GoogleApiClientModule;
import com.speakingchat.di.module.MainPresenterModule;
import com.speakingchat.di.module.PreferencesModule;
import com.speakingchat.di.module.RetrofitModule;
import com.speakingchat.di.module.RxEventBusModule;
import com.speakingchat.eventbus.RxEventBus;
import com.speakingchat.utils.AppPreferences;
import com.speakingchat.views.activities.MainActivity;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {AppContextModule.class, PreferencesModule.class, RetrofitModule.class, RxEventBusModule.class, MainPresenterModule.class})
public interface AppComponent {

	void inject(MainActivity activity);

	AppPreferences getAppPreferences();

	ActivityComponent createSignInComponent(GoogleApiClientModule module);

	Retrofit getRetrofit();

	RxEventBus getBus();
}
