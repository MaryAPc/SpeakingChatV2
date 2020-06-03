package com.speakingchat.presenters;

import com.speakingchat.views.interfaces.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    private V mView;

    public void attachView(V view) {
        this.mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public V getView() {
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public abstract void destroy();
}
