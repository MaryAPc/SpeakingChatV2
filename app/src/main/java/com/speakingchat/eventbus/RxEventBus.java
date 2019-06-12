package com.speakingchat.eventbus;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxEventBus {

    private final PublishSubject<EventType> mPublishSubject = PublishSubject.create();

    public void send(EventType eventType) {
        mPublishSubject.onNext(eventType);
    }

    public Observable<EventType> toObservable() {
        return mPublishSubject;
    }
}
