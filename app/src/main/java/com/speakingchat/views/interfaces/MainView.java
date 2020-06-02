package com.speakingchat.views.interfaces;

import com.speakingchat.views.Screens;

public interface MainView extends BaseView {
    void setCheckedNavigationItem(Screens.ScreenName selectedScreenName);

    void showSignInActivity();

    void inflateViewStubSignIn();
}
