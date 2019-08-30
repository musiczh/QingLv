package com.example.qinglv.AddPackage.contract;

import java.util.List;

public interface INoteTypeContract {

    interface IView<T> {
         void setList(List<T> list );
         void setErrorToast(String string);
    }
    interface IPresenter<T> {
        //view层调用
        void setList(List<T> list );
        void setErrorToast(String string);
        //model层调用
        void getDatas();
    }
    interface IModel {
        void getDatas();

    }
}
