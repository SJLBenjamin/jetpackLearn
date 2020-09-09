package com.example.jetpacklearn.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


//Activity的生命周期回调
public class ActivityLiftCycleCallBackLearn implements Application.ActivityLifecycleCallbacks {
    String TAG = "ActivityLiftCycleCallBackLearn";
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated");
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d(TAG,"onActivityStarted");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d(TAG,"onActivityResumed");

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d(TAG,"onActivityPaused");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d(TAG,"onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.d(TAG,"onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.d(TAG,"onActivityDestroyed");

    }
}
