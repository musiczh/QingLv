package com.example.qinglv.MainPackage.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 重写RecyclerView的滑动监听器，当滑到最后一项而且是手机在拖的时候加载更多
 */

public abstract class NewRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //获取最后一个完全显示的itemPosition,以及一共有多少项
        assert manager != null;
        int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
        int itemCount = manager.getItemCount();

        // 判断是否滑动到了最后一个item，并且是向上滑动
        if (lastItemPosition == (itemCount - 1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            //加载更多
            onLoadMore(lastItemPosition, itemCount);
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    /**
     * 加载更多回调
     */
    abstract void onLoadMore(int lastItemPosition, int itemCount);
}
