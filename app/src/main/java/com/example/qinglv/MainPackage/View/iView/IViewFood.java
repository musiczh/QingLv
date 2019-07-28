package com.example.qinglv.MainPackage.View.iView;

import com.example.qinglv.MainPackage.Entity.Food;

import java.util.List;

/**
 * 美食预览界面的接口
 */
public interface IViewFood {
    public void setList(List<Food> list , boolean isMore);
    public void setErrorToast(String string);
}
