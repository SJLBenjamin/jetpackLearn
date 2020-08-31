package com.example.jetpacklearn.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.jetpacklearn.R;

public class PagingActivity extends AppCompatActivity {
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        rv = (RecyclerView) findViewById(R.id.rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        //适配器
        final CustomAdapter customAdapter = new CustomAdapter(this);

        //setInitialLoadSizeHint第一页的加载的数据
        PagedList.Config build = new PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(true).setInitialLoadSizeHint(10).build();
        LiveData liveData = new LivePagedListBuilder(new CustomPageDataSourceFactory(new DataRepository()), build).build();

        rv.setAdapter(customAdapter);

        liveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                customAdapter.submitList((PagedList<DataBean>) o);
            }
        });
    }
}
