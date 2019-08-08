package com.example.qinglv.AddPackage.presenter;

import com.example.qinglv.AddPackage.contract.ICommitNoteContract;
import com.example.qinglv.AddPackage.model.CommitNoteModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

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

    }

    @Override
    public void setErrorToast(String string) {

    }

    @Override
    public void commitNote(Map params, List files) {
        mModel.commitNote(params,files);
    }


}
