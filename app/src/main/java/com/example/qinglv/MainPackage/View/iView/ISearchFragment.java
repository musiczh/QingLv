package com.example.qinglv.MainPackage.View.iView;

import java.util.List;

public interface ISearchFragment<T> {
    public void setList(List<T> list);
    public void setQuery(String query);
}
