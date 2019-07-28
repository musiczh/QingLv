package com.example.qinglv.MainPackage.Model.iModel;

import com.example.qinglv.MainPackage.Entity.Food;

import java.util.List;

/**
 * 美食预览碎片的model层接口
 */
public interface IModelFood {
    public void getData(int firstNum , int size , CallBack callBack);
    interface CallBack{
        void onSucceed(List<Food> list , boolean isMore);
        void onError(String errorType);
    }

}
