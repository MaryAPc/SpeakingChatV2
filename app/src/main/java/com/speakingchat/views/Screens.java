package com.speakingchat.views;

import com.speakingchat.views.fragments.AboutAppFragment;
import com.speakingchat.views.fragments.ChatFragment;
import com.speakingchat.views.fragments.SettingsFragment;
import com.speakingchat.views.fragments.SignInFragment;

import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static final class ChatScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return ChatFragment.newInstance();
        }
    }

    public static final class AboutScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return AboutAppFragment.newInstance();
        }
    }

    public static final class SettingsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return SettingsFragment.newInstance();
        }
    }

    public static final class SignInScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return SignInFragment.newInstance();
        }
    }

    public enum ScreenName {
        SIGN_IN, ABOUT_APP, CHAT, SETTINGS
    }
}
