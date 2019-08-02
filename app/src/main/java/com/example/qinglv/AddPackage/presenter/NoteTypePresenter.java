package com.example.qinglv.AddPackage.presenter;

import com.example.qinglv.AddPackage.BasePresenter.NoteTypeBasePresenter;
import com.example.qinglv.AddPackage.contract.NoteTypeContract;

import com.example.qinglv.AddPackage.model.NoteTypeModel;

import java.util.List;

public class NoteTypePresenter extends NoteTypeBasePresenter implements NoteTypeContract.IPresenter {

    private NoteTypeContract.IModel mModel;
    private NoteTypeContract.IView mView;

    public NoteTypePresenter(){
        mModel = new NoteTypeModel(this);
    }




    @Override
    public void setMyAdapter(final List list) {
        NoteTypeContract.IModel.CallBack callBack = new NoteTypeContract.IModel.CallBack() {
            @Override
            public void onSuccess(List list) {
                mView.setList(list);
            }

            @Override
            public void onError(String string) {

            }

        };

    }
}
