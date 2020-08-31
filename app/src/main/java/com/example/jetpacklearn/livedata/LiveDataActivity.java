package com.example.jetpacklearn.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jetpacklearn.R;
import com.example.jetpacklearn.livedata.LiveDataViewModel;

public class LiveDataActivity extends AppCompatActivity {
 String TAG ="LiveDataActivity";
    LiveDataViewModel liveDataViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        //创建LiveDataViewModel实例对象
        liveDataViewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);

        //创建回调
        liveDataViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG,"s==="+s);
            }
        });

       findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               liveDataViewModel.getCurrentName().setValue("1234");
           }
       });

    }


}
