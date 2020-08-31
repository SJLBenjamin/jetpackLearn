package com.example.jetpacklearn.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class CustomPageDataSourceFactory  extends DataSource.Factory<Integer,DataBean> {
   DataRepository repository;
    public CustomPageDataSourceFactory(DataRepository repository) {
        this.repository =repository;
    }

    @NonNull
    @Override
    public DataSource<Integer, DataBean> create() {
        return new CustomPageDataSource(repository);
    }


}
