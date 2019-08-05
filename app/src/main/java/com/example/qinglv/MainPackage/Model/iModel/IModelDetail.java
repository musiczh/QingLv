package com.example.qinglv.MainPackage.Model.iModel;

import com.example.qinglv.MainPackage.Entity.Path;

public interface IModelDetail<T> {
    public void getData(int id , CallBack<T> callBack);
    interface CallBack<T>{
        void onSucceed(T detail );
        void onError(String errorType);
    }
}
