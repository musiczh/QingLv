package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.bean.DetailBean;
import com.example.qinglv.MainPackage.inter.iApiService.PathDetailApiService;
import com.example.qinglv.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PathDetailModel implements IModelDetail<Path> {
    @Override
    public void getData(int id , final CallBack<Path> callBack) {
        RetrofitManager.getInstance().createRs(PathDetailApiService.class)
                .getPath(id)
                .enqueue(new Callback<DetailBean<Path>>() {
                    @Override
                    public void onResponse(@NonNull Call<DetailBean<Path>> call,
                                           @NonNull Response<DetailBean<Path>> response) {
                        if (response.body() != null) {
                            Path path = response.body().getMessage();
                            callBack.onSucceed(response.body().getMessage());
                        }else{
                            callBack.onError("好像出了一点小问题");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<DetailBean<Path>> call, @NonNull Throwable t) {
                        callBack.onError("好像出了点小问题");
                    }
                });
    }


}
