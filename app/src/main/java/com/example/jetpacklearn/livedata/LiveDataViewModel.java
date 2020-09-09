package com.example.jetpacklearn.livedata;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 提供MutableLiveData实例,MutableLiveData中的泛型才是需要用到的实体类,LiveData会自动跟随界面的生命周期,不会泄露
 */
public class LiveDataViewModel extends ViewModel {

    public MutableLiveData<String>  currentName;
    public ObservableField<String> name;

    public  MutableLiveData<String> getCurrentName(){
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }

    public  ObservableField<String> getName(){
        if (name == null) {
            name = new ObservableField<String>();
        }
        return name;
    }
}
