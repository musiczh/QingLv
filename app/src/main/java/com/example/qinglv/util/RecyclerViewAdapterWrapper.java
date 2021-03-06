package com.example.qinglv.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.qinglv.R;


/**
 * recyclerView的装饰，用于添加上拉加载功能的最后一项
 * 让最后一项在上拉的时候给用户提示和反馈
 * 创建完原始的adapter，直接放进这个工具类即可，用法例如：
 * adapterWrapper = new RecyclerViewAdapterWrapper(foodAdapter);
 *         recyclerView.setAdapter(adapterWrapper);
 * 这样就给适配器加上一个最后一项
 */

public class RecyclerViewAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    //两种类型的item
    private final int TYPE_FOOT = 1;
    private final int TYPE_ITEM = 2;

    //footView的三种状态，下拉加载，加载中，没有更多了
    private int itemState = 0;
    public final static int CONTINUE_DRAG = 1;
    public final static int LOADING = 2;
    public final static int NO_MORE = 3;
    public final static int NO_INTERNET = 4;
    public final static int NO_ARTICLE = 5;
    public final static int NO_COMMENT = 6;

    //新建一个最后一项布局的holder类，因为他的布局和其他的item不一样
    private class FootViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        FootViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView_item_foot_state);
        }
    }

    //构造函数传入一个adapter，再对这个adapter进行装饰
    public RecyclerViewAdapterWrapper(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
        mAdapter = adapter;
    }

    //抽象方法，必须重写，父adapter返回的是普通item的长度，加上footView所以要+1
    @Override
    public int getItemCount() {
        return mAdapter.getItemCount()+1;
    }

    //通过位置判断这是不是最够那个footView
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()){
            return TYPE_FOOT;
        }else{
            return TYPE_ITEM;
        }
    }

    //建立viewHolder的方法，根据i判断是哪种类型item，对应不同的布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOT){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_foot,viewGroup,false);
            return new FootViewHolder(view);
        }else{
            return mAdapter.onCreateViewHolder(viewGroup, i);
        }
    }

    //判断是否到最后一项了，再对最后一项分情况设置提示语
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FootViewHolder){
            FootViewHolder footViewHolder = (FootViewHolder)viewHolder;
            switch (itemState){
                case 0 : footViewHolder.textView.setText("");break;
                case CONTINUE_DRAG : footViewHolder.textView.setText("上拉加载更多");break;
                case LOADING : footViewHolder.textView.setText("正在加载中");break;
                case NO_MORE : footViewHolder.textView.setText("我是个有底线的列表");break;
                case NO_ARTICLE : footViewHolder.textView.setText("没有相关文章");break;
                case NO_INTERNET : footViewHolder.textView.setText("出了点小问题，请检查一下网络");break;
                case NO_COMMENT : footViewHolder.textView.setText("没有相关评论");break;
            }
        }else mAdapter.onBindViewHolder(viewHolder, i);

    }

    //设置footView的显示文字
    public void setItemState(int itemState , boolean isOnlyFoot) {
        this.itemState = itemState;
        if (isOnlyFoot){
            notifyItemChanged(getItemCount()-1);
        }else{
            notifyDataSetChanged();
        }
    }
}
