package com.example.qinglv.MainPackage.Model.iModel;

import com.example.qinglv.MainPackage.Entity.Food;

import java.util.List;

/**
 * 美食预览碎片的model层接口
 */
public interface IModelPager<T> {
    public void getData(int firstNum , int size , CallBack<T> callBack);
    interface CallBack<T>{
        void onSucceed(List<T> list , boolean isMore);
        void onError(String errorType);
    }

}
