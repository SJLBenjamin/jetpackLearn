package com.example.jetpacklearn;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //生命周期注册
        registerActivityLifecycleCallbacks(new ActivityLiftCycleCallBackLearn());

    }
}
