package com.example.qinglv.AddPackage.BasePresenter;



public class NoteTypeBasePresenter<V> {
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
