package com.example.qinglv.MainPackage.inter.iApiMvp;

public interface IViewDetail<T> {
     void setDetail(T detail);
     void onError(String errorType);
    void setHeart(boolean isHeart);
    void setCollection(boolean isStar);
}
