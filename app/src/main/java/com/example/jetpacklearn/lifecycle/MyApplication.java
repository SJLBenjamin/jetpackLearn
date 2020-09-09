package com.example.jetpacklearn.lifecycle;

import android.app.Application;

import com.example.jetpacklearn.lifecycle.ActivityLiftCycleCallBackLearn;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //生命周期注册
        registerActivityLifecycleCallbacks(new ActivityLiftCycleCallBackLearn());

    }
}
