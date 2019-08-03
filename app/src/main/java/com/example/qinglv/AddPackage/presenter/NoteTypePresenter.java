package com.example.qinglv.AddPackage.presenter;

import android.util.Log;

import com.example.qinglv.AddPackage.BasePresenter.NoteTypeBasePresenter;
import com.example.qinglv.AddPackage.contract.INoteTypeContract;
import com.example.qinglv.AddPackage.model.NoteTypeModel;
import java.util.List;

public class NoteTypePresenter extends NoteTypeBasePresenter<INoteTypeContract.IView> implements INoteTypeContract.IPresenter {

    private INoteTypeContract.IModel mModel;



    public NoteTypePresenter(){
        mModel = new NoteTypeModel(this);

    }

    @Override
    public void getDatas() {
        mModel.getDatas();
    }


    @Override
    public void setList(List list) {

        Log.d("isAttachView()","真假"+isAttachView());
        if(isAttachView()){
            getMvpView().setList(list);
        }

    }

    @Override
    public void setErrorToast(String string) {
        if(isAttachView()){
            getMvpView().setErrorToast("访问服务器错误");
        }
    }


}
