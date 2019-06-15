package com.speakingchat.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.speakingchat.R;
import com.speakingchat.SpeakingChatApplication;
import com.speakingchat.eventbus.EventType;
import com.speakingchat.eventbus.RxEventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SignInFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final int RC_AUTH_CODE = 11;

    @BindView(R.id.fragment_signin_button_sign_in)
    SignInButton mButton;

    private GoogleApiClient mApiClient;
    private Unbinder mUnbinder;
    private RxEventBus mEventBus;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mApiClient = SpeakingChatApplication.getAppComponent().createSignInComponent(new GoogleApiClientModule(getActivity(), this)).getGoogleApiClient();
        mEventBus = SpeakingChatApplication.getAppComponent().getBus();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_signin_button_sign_in:
                signIn();
                break;
        }
    }

    private void signIn() {
       mEventBus.send(EventType.ON_SIGN_IN);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
