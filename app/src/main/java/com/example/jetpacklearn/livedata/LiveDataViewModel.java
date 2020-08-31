package com.example.jetpacklearn.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<String>  currentName;

    public  MutableLiveData<String> getCurrentName(){
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }


}
