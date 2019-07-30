package com.example.qinglv.MainPackage.View.iView;

import com.example.qinglv.MainPackage.Entity.Food;

import java.util.List;

/**
 * 美食预览界面的接口
 */
public interface IViewPreview<T> {
    public void setList(List<T> list , boolean isMore ,boolean isRefresh);
    public void setErrorToast(String string);
}
