package com.example.qinglv.AddPackage.BasePresenter;

import android.view.View;

public class NoteTypeBasePresenter {
    private View view;

    protected void attachView(View view){
        this.view = view;
    }

    protected void detachView(){
        view = null;
    }

    protected View getMvpView(){
        return view;
    }

    protected boolean isAttachView(){
        return view != null;
    }
}
