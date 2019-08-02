package com.example.qinglv.AddPackage.model;

import com.example.qinglv.AddPackage.contract.NoteTypeContract;
import com.example.qinglv.AddPackage.entity.NoteType;
import com.example.qinglv.AddPackage.iApiService.NoteTypeApiService;
import com.example.qinglv.MainPackage.bean.PreviewBean;
import com.example.qinglv.util.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 游记种类的model层
 */

public class NoteTypeModel implements NoteTypeContract.IModel {

    private NoteTypeContract.IPresenter mPresenter;

    public NoteTypeModel(NoteTypeContract.IPresenter presenter){
        mPresenter = presenter;
    }



    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getDatas(final CallBack callBack) {
        RetrofitManager.getInstance().createRs(NoteTypeApiService.class)
                .getNoteType()
                .enqueue(new Callback<PreviewBean<NoteType>>() {
                    @Override
                    public void onResponse(Call<PreviewBean<NoteType>> call, Response<PreviewBean<NoteType>> response) {
                        //数据访问成功
                        assert response.body() != null;
                        boolean isMore = response.body().getResult().equals("success");
                        List<NoteType> noteTypeList = response.body().getMessage();
                        callBack.onSuccess(noteTypeList);
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<NoteType>> call, Throwable t) {
                        callBack.onError("访问服务器失败");
                    }
                });
    }






}
