package com.example.qinglv.MainPackage.inter.iApiMvp;

public interface IViewDetail<T> {
    public void setDetail(T detail);
    public void onError(String errorType);
    void setHeart(boolean isHeart);
    void setStar(boolean isStar);
}
