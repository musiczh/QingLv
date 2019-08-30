package com.example.qinglv.MainPackage.Model;

import android.support.annotation.NonNull;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Entity.TravelDetail;
import com.example.qinglv.MainPackage.bean.DetailBean;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiService.PathDetailApiService;
import com.example.qinglv.MainPackage.inter.iApiService.TravelDetailApiService;
import com.example.qinglv.util.RetrofitManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelDetailModel implements IModelDetail<TravelDetail> {
    @Override
    public void getData(int id , final CallBack<TravelDetail> callBack) {
        RetrofitManager.getInstance().createRs(TravelDetailApiService.class)
                .getTravelDetail(id)
                .enqueue(new Callback<DetailBean<TravelDetail>>() {
                    @Override
                    public void onResponse(@NotNull Call<DetailBean<TravelDetail>> call,
                                           @NotNull Response<DetailBean<TravelDetail>> response) {
                        if (response.body() != null){
                            callBack.onSucceed(response.body().getMessage());
                        }else{
                            callBack.onError("好像出了一点小问题");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<DetailBean<TravelDetail>> call, @NotNull Throwable t) {
                        callBack.onError("好像出了一点小问题");
                    }
                });
    }
}
