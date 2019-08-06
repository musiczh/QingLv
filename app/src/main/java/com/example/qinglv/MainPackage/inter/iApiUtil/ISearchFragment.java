package com.example.qinglv.MainPackage.inter.iApiUtil;

import java.util.List;

public interface ISearchFragment<T> {
    public void setList(List<T> list);
    public void setQuery(String query);
}
