package com.example.qinglv.MainPackage.inter.iApiMvp;

import java.util.List;

public interface IModelSearch<T> {
    public void getData(String key,int firstNum , int size , IModelPager.CallBack<T> callBack);
    interface CallBack<T>{
        void onSucceed(List<T> list , boolean isMore);
        void onError(String errorType);
    }
}
