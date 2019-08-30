package com.example.qinglv.AddPackage.model;

import android.util.Log;

import com.example.qinglv.AddPackage.contract.INoteTypeContract;
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

public class NoteTypeModel implements INoteTypeContract.IModel {

    private INoteTypeContract.IPresenter mPresenter;

    public NoteTypeModel(INoteTypeContract.IPresenter presenter){
        mPresenter = presenter;
    }



    //通过这个方法访问数据，并采用回调的方式在presenter中处理数据
    @Override
    public void getDatas() {
        RetrofitManager.getInstance().createRs(NoteTypeApiService.class)
                .getNoteType()
                .enqueue(new Callback<PreviewBean<NoteType>>() {
                    @Override
                    public void onResponse(Call<PreviewBean<NoteType>> call, Response<PreviewBean<NoteType>> response) {
                        //数据访问成功
                        Log.d("response","是否为空"+response);
                        assert response.body() != null;
                        boolean isMore = response.body().getResult().equals("success");
                        List<NoteType> noteTypeList = response.body().getMessage();

                        for (int i=0;i<noteTypeList.size();i++){
                            Log.d("for",""+noteTypeList.get(i).getName());
                        }

                        mPresenter.setList(noteTypeList);
                    }

                    @Override
                    public void onFailure(Call<PreviewBean<NoteType>> call, Throwable t) {
                        mPresenter.setErrorToast("访问服务器错误");
                    }
                });
    }
}
