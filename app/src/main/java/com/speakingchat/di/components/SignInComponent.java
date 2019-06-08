package com.speakingchat.di.components;


import com.google.android.gms.common.api.GoogleApiClient;
import com.speakingchat.di.annotations.ActivityScope;
import com.speakingchat.di.module.GoogleApiClientModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {GoogleApiClientModule.class})
public interface SignInComponent {

    GoogleApiClient getGoogleApiClient();

}
