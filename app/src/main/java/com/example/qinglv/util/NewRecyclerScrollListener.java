package com.example.qinglv.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 重写RecyclerView的滑动监听器，当滑到最后一项而且是手在拖的时候加载更多
 * 可以在view中给recyclerView设置上拉监听例如：
 *recyclerView.addOnScrollListener(new NewRecyclerScrollListener() {
 *  public void onLoadMore(int itemCount) {
 *      //方法体
 *   } });
 * 在方法中设置监听
 *
 * 注意：要结合另一个工具类RecyclerViewAdapterWrapper
 */

public abstract class NewRecyclerScrollListener extends RecyclerView.OnScrollListener {
    //这个参数设置是否可以继续下滑，当到底的时候就不可以继续下滑了
    public static Boolean IS_SCROLL = true;
    //判断是否在向上滑动
    private Boolean isDragUp = false;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //获取最后一个完全显示的itemPosition,以及一共有多少项
        assert manager != null;
        int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
        int itemCount = manager.getItemCount();

        // 判断是否滑动到了最后一个item，并且是向上滑动而且是手拉着向上滑动而不是惯性
        if (lastItemPosition == (itemCount - 1) && newState == RecyclerView.SCROLL_STATE_DRAGGING
                && IS_SCROLL && isDragUp) {
            //加载更多
            onLoadMore(itemCount);
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isDragUp = dy>0;
    }

    /**
     * 加载更多回调
     */
    public abstract void onLoadMore(int itemCount);
}
