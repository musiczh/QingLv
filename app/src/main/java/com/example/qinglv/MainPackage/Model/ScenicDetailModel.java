package com.example.qinglv.MainPackage.Model;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.bean.DetailBean;
import com.example.qinglv.MainPackage.inter.iApiService.ScenicDetailApiService;
import com.example.qinglv.util.RetrofitManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScenicDetailModel implements IModelDetail<Scenic> {
    @Override
    public void getData(int id, final CallBack<Scenic> callBack) {
        RetrofitManager.getInstance().createRs(ScenicDetailApiService.class)
                .getScenic(id)
                .enqueue(new Callback<DetailBean<Scenic>>() {
                    @Override
                    public void onResponse(@NotNull Call<DetailBean<Scenic>> call, @NotNull Response<DetailBean<Scenic>> response) {
                        if (response.body() != null) {
                            Scenic scenic = response.body().getMessage();
                            callBack.onSucceed(response.body().getMessage());
                        }else{
                            callBack.onError("好像出了一点小问题");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<DetailBean<Scenic>> call, @NotNull Throwable t) {
                        callBack.onError("好像出了一点小问题");
                    }
                });
    }
}
