package com.speakingchat.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.navigation.NavigationView;
import com.speakingchat.R;
import com.speakingchat.SpeakingChatApplication;
import com.speakingchat.di.module.GoogleApiClientModule;
import com.speakingchat.eventbus.EventType;
import com.speakingchat.eventbus.RxEventBus;
import com.speakingchat.presenters.MainPresenter;
import com.speakingchat.utils.AppPreferences;
import com.speakingchat.views.Screens;
import com.speakingchat.views.interfaces.MainView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import rx.Subscription;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener, MainView, View.OnClickListener {

    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.activity_main_drawer_navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_main_view_stub_sign_in)
    ViewStub mViewStubSignIn;

    @Inject
    AppPreferences mAppPreferences;

    @Inject
    RxEventBus mEventBus;

    @Inject
    MainPresenter mPresenter;

    Button mButton;

    private static final int RC_AUTH_CODE = 11;

    private Subscription mEventSubscription;
    private GoogleApiClient mApiClient;
    private Navigator mNavigator;
    private NavigatorHolder mNavigatorHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);


        SpeakingChatApplication.getAppComponent().inject(this);
        mApiClient = SpeakingChatApplication.getAppComponent().createSignInComponent(new GoogleApiClientModule(this, this)).getGoogleApiClient();

        mNavigator = new SupportAppNavigator(this, R.id.activity_main_app_bar_frame_layout_container);
        mNavigatorHolder = SpeakingChatApplication.getInstance().getNavigatorHolder();

        mPresenter.attachView(this);
        setupContent();


        subscribeEventListener();
    }

    private void setupContent(){
        if (!mAppPreferences.isSignedIn()) {
            //inflateViewStubSignIn();
            mPresenter.setupSignInLayout();
        } else {
            mPresenter.setupRootChatScreen();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigatorHolder.setNavigator(mNavigator);
    }

    @Override
    protected void onPause() {
        mNavigatorHolder.removeNavigator();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventSubscription.unsubscribe();

        mApiClient.stopAutoManage(this);
        mApiClient.unregisterConnectionFailedListener(this);
        mApiClient.disconnect();

        mPresenter.detachView();
        mPresenter.destroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chatFragment:
                mPresenter.chatItemClick();
                break;
            case R.id.aboutAppFragment:
                mPresenter.aboutAppItemClick();
                break;
            case R.id.settingsFragment:
                mPresenter.settingsItemClick();
                break;
            case R.id.signInFragment:
                mPresenter.signInClick();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewstub_layout_sign_in_button:
                mPresenter.signInClick();
                break;
        }
    }

    private void subscribeEventListener() {
        mEventSubscription = mEventBus.toObservable().subscribe(eventType -> {

            switch (eventType) {
                case ON_SIGN_IN:

                    break;
                case ON_SUCCESS_SIGN_IN:
                    Log.e("Main Activity", "sign in");
                    break;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mPresenter.backPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_AUTH_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                String authCode = acct.getServerAuthCode();
                Log.e("AUTH", authCode);

                mEventBus.send(EventType.ON_SUCCESS_SIGN_IN);
            } else {
                //TODO обработка неудачного логина
                mEventBus.send(EventType.ON_ERROR_SIGN_IN);
                Log.e("AUTH", "Error");
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void setCheckedNavigationItem(Screens.ScreenName selectedScreenName) {
        switch (selectedScreenName) {
            case SIGN_IN:
                mNavigationView.setCheckedItem(R.id.signInFragment);
                break;
            case ABOUT_APP:
                mNavigationView.setCheckedItem(R.id.aboutAppFragment);
                break;
            case SETTINGS:
                mNavigationView.setCheckedItem(R.id.settingsFragment);
                break;
            case CHAT:
                mNavigationView.setCheckedItem(R.id.chatFragment);
                break;
        }
    }

    @Override
    public void showSignInActivity() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mApiClient);
        startActivityForResult(signInIntent, RC_AUTH_CODE);
    }

    @Override
    public void inflateViewStubSignIn() {
        View inflatedSignIn = mViewStubSignIn.inflate();
        SignInButton signInButton = inflatedSignIn.findViewById(R.id.viewstub_layout_sign_in_button);
        signInButton.setOnClickListener(this);
    }
}
