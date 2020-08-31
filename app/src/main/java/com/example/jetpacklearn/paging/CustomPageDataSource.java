package com.example.jetpacklearn.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

public class CustomPageDataSource extends PageKeyedDataSource<Integer, DataBean> {
    DataRepository repository;

    public CustomPageDataSource(DataRepository repository) {
            this.repository =repository;
    }

    //初始化
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
        List<DataBean> dataBeans = repository.loadData(params.requestedLoadSize);
        callback.onResult(dataBeans,null,2);
    }

    //加载前一页
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        List<DataBean> dataBeans = repository.loadPageData(params.key, params.requestedLoadSize);
        callback.onResult(dataBeans, params.key - 1);
    }

    //加载后一页
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        List<DataBean> dataBeans = repository.loadPageData(params.key, params.requestedLoadSize);
        callback.onResult(dataBeans, params.key + 1);
    }

}
