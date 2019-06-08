package com.speakingchat.views.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.navigation.NavigationView;
import com.speakingchat.R;
import com.speakingchat.SpeakingChatApplication;
import com.speakingchat.di.module.GoogleApiClientModule;
import com.speakingchat.utils.AppPreferences;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.activity_main_drawer_navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @Inject
    AppPreferences mAppPreferences;

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SpeakingChatApplication.getAppComponent().inject(this);



        setSupportActionBar(mToolbar);

        mNavController = Navigation.findNavController(this, R.id.activity_main_app_bar_nav_host_fragment);

        if (!mAppPreferences.isSignedIn()) {


            NavInflater inflater = mNavController.getNavInflater();
            NavGraph graph = inflater.inflate(R.navigation.nav_graph);
            graph.setStartDestination(R.id.signInFragment);
            mNavController.setGraph(graph);
        }

        NavigationUI.setupWithNavController(mNavigationView, mNavController);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawerLayout);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mNavController.getCurrentDestination().getId() != mNavController.getGraph().getStartDestination()) {
                    mNavController.popBackStack();
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        mNavController.navigateUp();
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
