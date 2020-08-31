package com.example.jetpacklearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jetpacklearn.databinding.ActivityMainBinding;
import com.example.jetpacklearn.livedata.LiveDataActivity;
import com.example.jetpacklearn.paging.PagingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    String TAG ="MainActivity";
    Student student =new Student("宋炯乐",3);
    Student student1 =new Student("宋大神",24);
    StudentObservable studentObservable=new StudentObservable(new ObservableField<String>("男"),new ObservableInt(70));

    //设置map类型的值,类型需要和xml文件中设置的一致
    ObservableArrayMap<String, Object> stringIntegerObservableArrayMap = new ObservableArrayMap<String, Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //activityMainBinding.tvName.setText("123");
        activityMainBinding.setStudent(student);
        activityMainBinding.setStudent1(student1);
        activityMainBinding.setStudentObservable(studentObservable);

        //设置hobbit为key的值
        stringIntegerObservableArrayMap.put("hobbit","篮球");
        //设置grade为key的值
        stringIntegerObservableArrayMap.put("grade",98);
        activityMainBinding.setStudentObservableMap(stringIntegerObservableArrayMap);
        student.setAge(88);

        //loadImage((ImageView) findViewById(R.id.iv_show),"123456",getDrawable(R.drawable.ic_launcher),getDrawable(R.drawable.ic_launcher));

        /*
        * BottomNavigationView以下是是
        *
        * */
       final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bv_dh);

       //去掉不显示图片默认颜色
       navigationView.setItemIconTintList(null);
       //切换的时候改变图标
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //重置未选择的控件
                resetToDefaultIcon(navigationView,menuItem.getItemId());
                switch (menuItem.getItemId()){
                    case R.id.main_my:
                        menuItem.setIcon(R.drawable.record_add_icon);
                        //打印双向绑定的结果,和navigationView没任何关系
                        Log.d(TAG,"name==="+student1.getName());
                        break;
                    case R.id.main_music:
                        menuItem.setIcon(R.drawable.record_add_icon);
                        break;
                    case R.id.main_wallpaper:
                        menuItem.setIcon(R.drawable.record_add_icon);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //添加生命周期观察者
        getLifecycle().addObserver(new LifeCycleLearn());


        startActivity(new Intent(MainActivity.this, PagingActivity.class));
        // startActivity(new Intent(MainActivity.this, LiveDataActivity.class));

    }

    //将条目id储存起来
    int navigationId[]={R.id.main_my,R.id.main_music,R.id.main_wallpaper};
    //重置未选择的控件
    private void resetToDefaultIcon(BottomNavigationView navigation,int id) {
        for (int i=0;i<navigationId.length;i++){
            if(id!=navigationId[i]){
                MenuItem home =  navigation.getMenu().findItem(navigationId[i]);
                home.setIcon(R.mipmap.ic_launcher);
            }
        }
    }



    /**必须声明为static,不然无法自动被调用
     * @param view
     * @param url  android:imageUrl
     *             requireAll是否需要全部参数
     */
    @BindingAdapter(value = {"imageUrl","age","student"},requireAll = false)
    public static void loadImage(ImageView view, String url,int age,Student student){
        //Glide.with(view.getContext()).load(url).into(view);
        Toast.makeText(view.getContext(),"url=="+url+"  age=="+age+"student name=="+student.name,Toast.LENGTH_SHORT).show();
    }

/*    *//**
     * @param view
     * @param url  android:imageUrl
     * @param error android:error
     * @param placeHolder android:placeHolder的值就是placeHolder设置的
     *//*
    @BindingAdapter(value = {"android:imageUrl",  "android:error","android:placeHolder"},requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable error,Drawable placeHolder){
        //Glide.with(view.getContext()).load(url).into(view);
        Toast.makeText(view.getContext(),"url=="+url,Toast.LENGTH_SHORT).show();
    }*/

}
