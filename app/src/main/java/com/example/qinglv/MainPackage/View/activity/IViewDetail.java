package com.example.qinglv.MainPackage.View.activity;

import com.example.qinglv.MainPackage.Entity.Path;

public interface IViewDetail<T> {
    public void setDetail(T detail);
    public void onError(String errorType);
}
