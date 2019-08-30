package com.example.qinglv.AddPackage.presenter;

/**
 * 提交游记的BasePresenter层
 * @param <V>
 */
public class CommitNoteBasePresenter<V>  {
    private V view;

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        view = null;
    }

    public V getMvpView(){
        return view;
    }

    public boolean isAttachView(){
        return view != null;
    }
}
