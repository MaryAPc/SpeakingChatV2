package com.speakingchat.presenters;

import com.speakingchat.SpeakingChatApplication;
import com.speakingchat.utils.AppPreferences;
import com.speakingchat.views.Screens;
import com.speakingchat.views.interfaces.MainView;

import ru.terrakok.cicerone.Router;


public class MainPresenter extends BasePresenter<MainView> {

    private Router mRouter;
    private AppPreferences mAppPreferences;
    private NavState mNavState;

    public MainPresenter() {
        mRouter = SpeakingChatApplication.getInstance().getRouter();
        mAppPreferences = SpeakingChatApplication.getAppComponent().getAppPreferences();
    }

    @Override
    public void destroy() {

    }

    public void setupSignInLayout() {
        getView().inflateViewStubSignIn();
        getView().setCheckedNavigationItem(Screens.ScreenName.SIGN_IN);
    }

    public void setupRootSignInScreen() {
        mRouter.newRootScreen(new Screens.SignInScreen());
        mNavState = NavState.ROOT_SCREEN;
        getView().setCheckedNavigationItem(Screens.ScreenName.SIGN_IN);
    }

    public void setupRootChatScreen() {
        mRouter.newRootScreen(new Screens.ChatScreen());
        mNavState = NavState.ROOT_SCREEN;
        getView().setCheckedNavigationItem(Screens.ScreenName.CHAT);
    }

    public void chatItemClick() {
        mRouter.navigateTo(new Screens.ChatScreen());
        mNavState = NavState.NAVIGATED;
        getView().setCheckedNavigationItem(Screens.ScreenName.CHAT);
    }

    public void aboutAppItemClick() {
        mRouter.navigateTo(new Screens.AboutScreen());
        mNavState = NavState.NAVIGATED;
        getView().setCheckedNavigationItem(Screens.ScreenName.ABOUT_APP);
    }

    public void settingsItemClick() {
        mRouter.navigateTo(new Screens.SettingsScreen());
        mNavState = NavState.NAVIGATED;
        getView().setCheckedNavigationItem(Screens.ScreenName.SETTINGS);
    }

    public void signInClick() {
        getView().showSignInActivity();
    }

    public void backPressed() {
        if (mNavState == NavState.ROOT_SCREEN) {
            mRouter.exit();
        } else {
            if (mAppPreferences.isSignedIn()) {
                mRouter.backTo(new Screens.ChatScreen());
                mNavState = NavState.ROOT_SCREEN;
                getView().setCheckedNavigationItem(Screens.ScreenName.CHAT);
            } else {
                mRouter.backTo(new Screens.SignInScreen());
                mNavState = NavState.ROOT_SCREEN;
                getView().setCheckedNavigationItem(Screens.ScreenName.SIGN_IN);
            }
        }
    }

    private enum NavState {
        ROOT_SCREEN, NAVIGATED
    }
}
