package com.example.jetpacklearn.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jetpacklearn.R;
import com.example.jetpacklearn.databinding.ActivityLiveDataBinding;
import com.example.jetpacklearn.databinding.ActivityMainBinding;
import com.example.jetpacklearn.livedata.LiveDataViewModel;

public class LiveDataActivity extends AppCompatActivity {
    String TAG ="LiveDataActivity";
    LiveDataViewModel liveDataViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 //       setContentView(R.layout.activity_live_data);
//
        ActivityLiveDataBinding activityLiveDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);
        //生命周期跟随当前Activity
        activityLiveDataBinding.setLifecycleOwner(this);

        //创建LiveDataViewModel实例对象,此viewModel的跟随当前的Activity
        liveDataViewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);

        //ViewModel中owner如果是一个对象,那么就是单例
        LiveDataViewModel liveDataViewModel2=new ViewModelProvider(this).get(LiveDataViewModel.class);

        //从打印可以看出两个变量指向的是同一块空间
        if(liveDataViewModel==liveDataViewModel2){
            Log.d(TAG,"liveDataViewModel===liveDataViewModel2");
        }

        //设置
        activityLiveDataBinding.setVm(liveDataViewModel);

        //创建回调
        liveDataViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG,"s==="+s);
            }
        });

        liveDataViewModel2.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG,"liveDataViewModel2==="+s);
            }
        });

        //修改值
       findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //LiveData修饰的变量,DataBinding无法生效
               liveDataViewModel.getCurrentName().setValue("1234");
               //非liveData修饰的变量
               liveDataViewModel.getName().set("1234");
           }
       });
    }

}
