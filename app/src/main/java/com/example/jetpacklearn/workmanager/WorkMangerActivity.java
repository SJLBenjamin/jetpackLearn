package com.example.jetpacklearn.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jetpacklearn.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
* https://zhuanlan.zhihu.com/p/78599394
* */
public class WorkMangerActivity extends AppCompatActivity {
    String TAG ="WorkMangerActivity";

    //设置触发条件
    Constraints constraints = new Constraints.Builder()
            .setRequiresCharging(false)//设置在设备不在充电情况下也能执行
            .setRequiredNetworkType(NetworkType.CONNECTED)//设置网络连接的时候才执行
            .setRequiresBatteryNotLow(true)//且电池电量充足的状态
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manger);

      findViewById(R.id.bt_work).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              /*
              * setConstraints设置触发条件
              * setInitialDelay 符合触发条件后，设置延迟10秒执行
              * setBackoffCriteria 自定义指数退避策略。
              * addTag("UploadTag")  设置tag,可以通过此跟踪任务的状态WorkManager.getWorkInfosByTagLiveData(String tag)或者取消任务WorkManager.cancelAllWorkByTag(String tag)。
              *
              * */

              OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(MyWork.class)
                      .setConstraints(constraints)
                      .setInitialDelay(10, TimeUnit.SECONDS)
                      .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                      .addTag("test")
                      .build();
              WorkManager.getInstance(WorkMangerActivity.this).enqueue(build);
          }
      });

        //通过Tga观察还存在的的work
        WorkManager.getInstance(WorkMangerActivity.this).getWorkInfosByTagLiveData("test").observe(WorkMangerActivity.this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                Log.d(TAG,"workInfos==="+workInfos.size());
            }
        });


    }

}
