package com.example.jetpacklearn.paging;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpacklearn.R;

public class CustomAdapter extends PagedListAdapter<DataBean, CustomAdapter.CustomViewHolder> {

    Context mContext ;

    public CustomAdapter( Context context) {
        super(itemCallback);
        mContext =context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView id;
        DataBean dataBean;


        /**
         * @param itemView 是布局文件的解析后得到的view,后续可以直接通过这个对象去查找文件中的控件
         */
        public CustomViewHolder(@NonNull ViewGroup itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.tv_id);
            name = (TextView)itemView.findViewById(R.id.tv_name);
        }

      public void bindTo(DataBean item){
            name.setText(item.name);
            id.setText(item.id+"");
            dataBean = item;
        }
    }


    /**
     *
     * 初始化holder,解析布局文件
     * @param parent 父对象
     * @param viewType  用来设置不同布局
     * @return
     */
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            //此处就是我想要的布局
            Log.d("onCreateViewHolder","viewType=="+viewType);
        }

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder((ViewGroup) inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //PagedListAdapter创建了一个数据List,用来存储dataBean,通过getItem可以获取此对象
        holder.bindTo(getItem(position));
    }


    //返回值是onCreateViewHolder中的viewType
    @Override
    public int getItemViewType(int position) {
        //如果当前位置的值是我想改变布局的值,那么就将当前的位置的布局文件转化的id记录下来
        if(getItem(position).id==5){
         return   1;
        }else {
          return  0;
        }

    }

    //判断两个对象是否相等
    public static   DiffUtil.ItemCallback<DataBean> itemCallback = new DiffUtil.ItemCallback<DataBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            return oldItem.name.equals(newItem.name);
        }
    };
}
