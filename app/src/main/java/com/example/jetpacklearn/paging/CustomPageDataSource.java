package com.example.jetpacklearn.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

public class CustomPageDataSource extends PageKeyedDataSource<Integer, DataBean> {
    String TAG ="CustomPageDataSource";
    DataRepository repository;

    public CustomPageDataSource(DataRepository repository) {
            this.repository =repository;
    }

    //初始化
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
        List<DataBean> dataBeans = repository.loadData(params.requestedLoadSize);
        if(dataBeans!=null){
        //设置前一页是null,后一页是2,那么当前页就是1
        callback.onResult(dataBeans,null,2);
    }
    }

    //加载前一页
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        Log.d(TAG,"loadBefore params.key=="+params.key);
        List<DataBean> dataBeans = repository.loadPageData(params.key, params.requestedLoadSize);
        if(dataBeans!=null) {
            //设置页数,加载完成后设置下次调用key的值设置为原先值-1
            callback.onResult(dataBeans, params.key - 1);
        }
    }

    //加载后一页,params.key是自己设置的,默认值是loadInitial中调用 callback.onResult设置的,此处为2
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        Log.d(TAG,"loadAfter params.key=="+params.key);
        //此处默认为2
        List<DataBean> dataBeans = repository.loadPageData(params.key, params.requestedLoadSize);
        if(dataBeans!=null) {
            //设置页数,加载完成后设置下次调用key的值,设置为原先值+1,如果默认值是2,那么下次加载下一页的值为3
            callback.onResult(dataBeans, params.key+1 );
        }
    }

}
