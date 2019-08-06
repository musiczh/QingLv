package com.example.qinglv.MainPackage.inter.iApiMvp;

import java.lang.ref.WeakReference;

/**
 *  所有presenter的基类，弱引用对应的view实例，防止内存泄漏
 * @param <V> 泛型V是该presenter对应的IView类型
 */
public abstract class BasePresenter<V> {
    private WeakReference<V> mIView;

    //这里采用了弱引用法。防止活动没调用onDestroy方法的时候持续引用
    //建立关联
    public void attachView(V iView){
        mIView = new WeakReference<V>(iView);
    }

    //获得关联的View
    public V getView(){
        return mIView.get();
    }

    //解除关联View
    public void detachView(){
        if (mIView!=null){
            mIView.clear();
            mIView = null;
        }
    }

    //判断是否关联
    public boolean isAttached(){
        return (mIView!=null && mIView.get()!=null);
    }


}
