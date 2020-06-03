package com.speakingchat.di.module;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.speakingchat.R;
import com.speakingchat.di.annotations.ActivityScope;

import androidx.fragment.app.FragmentActivity;
import dagger.Module;
import dagger.Provides;


@Module
public class GoogleApiClientModule {

    private FragmentActivity mActivity;

    private GoogleApiClient.OnConnectionFailedListener mOnConnectionFailedListener;

    public GoogleApiClientModule(FragmentActivity activity, GoogleApiClient.OnConnectionFailedListener listener) {
        this.mActivity = activity;
        this.mOnConnectionFailedListener = listener;
    }

    @Provides
    @ActivityScope
    GoogleApiClient provideGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode(mActivity.getString(R.string.server_client_id))
                .requestEmail()
                .requestScopes(new Scope("https://www.googleapis.com/auth/youtube.readonly"))
                .build();

        return new GoogleApiClient.Builder(mActivity)
                .enableAutoManage(mActivity, mOnConnectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
}
