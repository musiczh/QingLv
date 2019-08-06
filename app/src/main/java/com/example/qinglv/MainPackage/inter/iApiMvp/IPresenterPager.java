package com.example.qinglv.MainPackage.inter.iApiMvp;

/**
 * 美食预览界面的presenter接口
 */
public interface IPresenterPager {
    public void refreshRecycler(int firstNum , int size , boolean isRefresh);

    public void searchKry(String key ,int firstNum , int size , boolean isRefresh);
}
