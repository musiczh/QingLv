package com.example.qinglv.AddPackage.contract;


import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;


public class ICommitNoteContract {

  public   interface IView<T> {

        void setErrorToast(String string);
        void onSuccess();
    }
  public  interface IPresenter<T> {
        //view层调用
        void onSuccess();
        void setErrorToast(String string);
        //model层调用,
        void commitPhotoNote(Map<String, RequestBody> params,List<MultipartBody.Part>  files);
      void commitNote(RequestBody body);
    }
    public interface IModel {
        void commitPhotoNote(Map<String, RequestBody> params, List<MultipartBody.Part> files);
        void commitNote(RequestBody body);

    }
}
