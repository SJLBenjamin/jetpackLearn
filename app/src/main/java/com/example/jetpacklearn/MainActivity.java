package com.example.jetpacklearn;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jetpacklearn.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    Student student =new Student("宋炯乐",3);
    StudentObservable studentObservable=new StudentObservable(new ObservableField<String>("男"),new ObservableInt(70));

    //设置map类型的值,类型需要和xml文件中设置的一致
    ObservableArrayMap<String, Object> stringIntegerObservableArrayMap = new ObservableArrayMap<String, Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //activityMainBinding.tvName.setText("123");
        activityMainBinding.setStudent(student);
        activityMainBinding.setStudentObservable(studentObservable);

        //设置hobbit为key的值
        stringIntegerObservableArrayMap.put("hobbit","篮球");
        //设置grade为key的值
        stringIntegerObservableArrayMap.put("grade",98);
        activityMainBinding.setStudentObservableMap(stringIntegerObservableArrayMap);
        student.setAge(88);

        loadImage((ImageView) findViewById(R.id.iv_show),"123456",getDrawable(R.drawable.ic_launcher),getDrawable(R.drawable.ic_launcher));

       final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bv_dh);

       //去掉不显示图片默认颜色
       navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //重置未选择的控件
                resetToDefaultIcon(navigationView,menuItem.getItemId());
                switch (menuItem.getItemId()){
                    case R.id.main_my:
                        menuItem.setIcon(R.drawable.record_add_icon);
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


    /**
     * @param view
     * @param url  android:imageUrl
     * @param error android:error
     * @param placeHolder android:placeHolder的值就是placeHolder设置的
     */
    @BindingAdapter(value = {"android:imageUrl",  "android:error","android:placeHolder"},requireAll = true)
    public static void loadImage(ImageView view, String url, Drawable error,Drawable placeHolder){
        //Glide.with(view.getContext()).load(url).into(view);
        Toast.makeText(view.getContext(),"url=="+url,Toast.LENGTH_SHORT).show();
    }

}
