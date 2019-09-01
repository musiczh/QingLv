package com.example.qinglv.MainPackage.inter.iApiMvp;

import java.util.List;

/**
 * 美食预览界面的接口
 */
public interface IViewPreview<T> {
    public void setList(List<T> list , boolean isMore ,boolean isRefresh);
    public void setErrorToast(String string , int footType);
    public void setQuery(String string);
}
