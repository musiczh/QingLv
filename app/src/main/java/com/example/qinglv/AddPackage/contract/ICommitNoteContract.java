package com.example.qinglv.AddPackage.contract;


import java.util.List;

import okhttp3.MultipartBody;


public class ICommitNoteContract {

  public   interface IView<T> {
        void commitNote(int id, String title, List<MultipartBody.Part> photos, String content, int tabId);
        void setErrorToast(String string);
        void onSuccess();
    }
  public  interface IPresenter<T> {
        //view层调用
        void onSuccess();
        void setErrorToast(String string);
        //model层调用
        void commitNote(int id, String title, List<MultipartBody.Part> photos,String content,int tabId);
    }
    public interface IModel {
        void commitNote(int id, String title, List<MultipartBody.Part> photos,String content,int tabId);

    }
}
