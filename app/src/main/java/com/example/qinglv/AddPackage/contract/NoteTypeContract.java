package com.example.qinglv.AddPackage.contract;

import java.util.List;

public interface NoteTypeContract {

    interface IView {
         void setList(List list );
         void setErrorToast(String string);
    }
    interface IPresenter {
        void setMyAdapter(List list);
    }
    interface IModel {
        void getDatas(CallBack callBack);
        interface CallBack{
            void onSuccess(List list);
            void onError(String string);
        }
    }
}
