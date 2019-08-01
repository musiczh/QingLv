package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.iModel.IModelPathDetail;
import com.example.qinglv.MainPackage.bean.DetailBean;
import com.example.qinglv.MainPackage.iApiService.PathDetailApiService;
import com.example.qinglv.MainPackage.iApiService.PathPreviewApiService;
import com.example.qinglv.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PathDetailModel implements IModelPathDetail {
    @Override
    public void getData(int id , final CallBack callBack) {
        RetrofitManager.getInstance().createRs(PathDetailApiService.class)
                .getPath(id)
                .enqueue(new Callback<DetailBean<Path>>() {
                    @Override
                    public void onResponse(@NonNull Call<DetailBean<Path>> call,
                                           @NonNull Response<DetailBean<Path>> response) {
                        assert response.body() != null;
                        Path path = response.body().getMessage();
                        callBack.onSucceed(response.body().getMessage());
                    }

                    @Override
                    public void onFailure(@NonNull Call<DetailBean<Path>> call, @NonNull Throwable t) {
                        callBack.onError("好像出了点小问题");
                    }
                });
    }


}
