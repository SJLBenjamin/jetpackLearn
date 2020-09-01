package com.example.jetpacklearn.paging;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//提供数据,模拟后台提供的返回数据
public class DataRepository {
    String TAG = "DataRepository";
    List<DataBean>  dataBeans = new ArrayList<>();

    public DataRepository() {
        for(int i =0;i<100;i++){
            dataBeans.add(new DataBean(i,"名字为"+i));
        }
    }

    public   List<DataBean> loadData(int size){
        Log.d(TAG,"loadData size=="+size);
        return dataBeans.subList(0,size);
    }

    public   List<DataBean> loadData(int index ,int size){
        Log.d(TAG,"loadData size=="+size+"  index=="+index);
        if(index>=dataBeans.size()-1 ||index <1){
            return null;
        }
        if(index+size>dataBeans.size()){
            return dataBeans.subList(index + 1, dataBeans.size());
        }
        return dataBeans.subList(index + 1, index + size);
    }



    List<DataBean> loadPageData(int page,int size) {

        int  totalPage = 0;

         if (dataBeans.size() % size == 0) {
             totalPage = dataBeans.size() / size;
        } else {
             totalPage =
                     dataBeans.size() / size + 1;
        }

        if (page > totalPage || page < 1) {
            Log.d(TAG,"page > totalPage || page < 1   loadPageData size=="+size+"  page=="+page);
            return null;
        }

        if (page == totalPage) {
            Log.d(TAG,"page == totalPage  loadPageData size=="+size+"  page=="+page);
            return dataBeans.subList((page - 1) * size, dataBeans.size());
        }
        Log.d(TAG,"loadPageData size=="+size+"  page=="+page);
        return dataBeans.subList((page - 1) * size, page * size);
    }


}
