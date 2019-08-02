package com.example.qinglv.MainPackage.Model.iModel;

import com.example.qinglv.MainPackage.Entity.Path;

public interface IModelPathDetail {
    public void getData(int id , CallBack callBack);
    interface CallBack{
        void onSucceed(Path path );
        void onError(String errorType);
    }
}
