package com.example.qinglv.UserPackage.Presenter;

public class BasePresenter<V> {

    private V view;

    protected void attachView(V view) {
        this.view = view;
    }


    protected void detachView() {
        view = null;
    }


    protected V getMvpView() {
        return view;
    }


    protected boolean isAttachView() {
        return view != null;
    }

}
