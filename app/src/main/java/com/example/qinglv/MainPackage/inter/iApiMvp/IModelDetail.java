package com.example.qinglv.MainPackage.inter.iApiMvp;

public interface IModelDetail<T> {
    public void getData(int id , CallBack<T> callBack);
    public void isStar(int typeId , CallBackStar callBackStar);
    interface CallBack<T>{
        void onSucceed(T detail );
        void onError(String errorType);
    }
    interface CallBackStar{
        void onSucceed(boolean isStar);
        void onError(String errorType);
    }

}
