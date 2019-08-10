package com.example.qinglv.AddPackage.presenter;

import com.example.qinglv.AddPackage.contract.ICommitNoteContract;
import com.example.qinglv.AddPackage.model.CommitNoteModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 提交游记的Presenter层
 */

public class CommitNotePresenter extends CommitNoteBasePresenter<ICommitNoteContract.IView> implements ICommitNoteContract.IPresenter {
    private ICommitNoteContract.IModel mModel;

    public CommitNotePresenter(){
        mModel = new CommitNoteModel(this);

    }


    @Override
    public void onSuccess() {
        if(isAttachView()){
            getMvpView().onSuccess();
        }
    }

    @Override
    public void setErrorToast(String string) {
        if(isAttachView()){
            getMvpView().setErrorToast("访问服务器错误");
        }
    }

    //无照片时调用
    @Override
    public void commitNote(RequestBody body) {
        mModel.commitNote(body);
    }

    //有照片时调用
    @Override
    public void commitPhotoNote(Map params, List files) {
        mModel.commitPhotoNote(params,files);
    }


}
