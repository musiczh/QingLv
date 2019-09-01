package com.example.qinglv.UserPackage.Presenter;

public class BasePresenter<V> {

    private V view;

    protected void attachView(V view) {
        this.view = view;
    }


    protected void detachView() {
        view = null;
    }


    public V getMvpView() {
        return view;
    }


    public boolean isAttachView() {
        return view != null;
    }

}
