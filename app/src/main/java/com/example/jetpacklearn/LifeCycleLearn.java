package com.example.jetpacklearn;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeCycleLearn implements LifecycleObserver {
    String tag ="LifeCycleLearn";
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void startLocation(){
        Log.d(tag,"startLocation");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stopLocation(){
        Log.d(tag,"stopLocation");
    }

}
